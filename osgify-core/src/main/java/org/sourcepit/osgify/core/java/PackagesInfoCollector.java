/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java;

import static java.util.Collections.sort;
import static java.util.Collections.unmodifiableCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.osgify.core.model.java.JavaFile;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.model.java.JavaType;
import org.sourcepit.osgify.core.model.java.Resource;
import org.sourcepit.osgify.core.model.java.ResourceVisitor;

public class PackagesInfoCollector
{
   public PackagesInfo collect(JavaResourceBundle jBundle, List<JavaResourceBundle> classPath)
   {
      final Map<String, RequiredPackages> packageToRequiredPackages = collectPackageReferences(jBundle, classPath);

      final List<String> packages = new ArrayList<String>(packageToRequiredPackages.size());
      final RequiredPackageBuilder bundleRefs = new RequiredPackageBuilder();
      for (Entry<String, RequiredPackages> entry : packageToRequiredPackages.entrySet())
      {
         final String packageName = entry.getKey();
         packages.add(packageName);

         final RequiredPackages refs = entry.getValue();
         bundleRefs.addInheritedPackages(refs.getInherited());
         bundleRefs.addInvokedPackages(refs.getInvoked());
      }

      sort(packages);

      return new PackagesInfo(unmodifiableCollection(packages), bundleRefs.build(),
         Collections.unmodifiableMap(packageToRequiredPackages));
   }

   private static Map<String, RequiredPackages> collectPackageReferences(final JavaResourceBundle jBundle,
      final List<JavaResourceBundle> classPath)
   {
      final Map<String, RequiredPackageBuilder> packageToRefsMap = new HashMap<String, RequiredPackageBuilder>();

      jBundle.accept(new ResourceVisitor()
      {
         @Override
         public boolean visit(Resource resource)
         {
            if (resource instanceof JavaResourcesRoot)
            {
               packageToRefsMap.put("", new RequiredPackageBuilder());
               return true;
            }

            if (resource instanceof JavaPackage)
            {
               packageToRefsMap.put(((JavaPackage) resource).getQualifiedName(), new RequiredPackageBuilder());
               return true;
            }

            if (resource instanceof JavaFile)
            {
               final JavaFile jFile = (JavaFile) resource;
               final JavaPackage jPackage = jFile.getParentPackage();
               if (jPackage != null)
               {
                  final RequiredPackageBuilder refs = packageToRefsMap.get(jPackage.getQualifiedName());
                  visit(refs, jPackage, jFile, jFile.getType(), classPath);
               }
            }

            return false;
         }

         private void visit(RequiredPackageBuilder refs, JavaPackage jPackage, JavaFile jFile, JavaType jType,
            List<JavaResourceBundle> classPath)
         {
            final Set<String> qualifiedTypeReferences = new HashSet<String>();
            collectReferencedTypes(refs, jFile, qualifiedTypeReferences);

            // HACK must be recursive
            addSuperTypeAndInterfaces(refs, jType, qualifiedTypeReferences);
            for (String qualifiedType : new HashSet<String>(qualifiedTypeReferences))
            {
               JavaType resolveJavaType = resolveJavaType(classPath, qualifiedType);
               if (resolveJavaType != null)
               {
                  addSuperTypeAndInterfaces(refs, resolveJavaType, qualifiedTypeReferences);
               }
            }
         }

         private void addSuperTypeAndInterfaces(final RequiredPackageBuilder refs, JavaType jType,
            final Set<String> qualifiedTypeReferences)
         {
            Annotation annotation = jType.getAnnotation("superclassName");
            if (annotation != null)
            {
               final Set<String> typeNames = annotation.getReferences().keySet();
               refs.addInheritedTypes(typeNames);
               qualifiedTypeReferences.addAll(typeNames);
            }

            annotation = jType.getAnnotation("interfaceNames");
            if (annotation != null)
            {
               final Set<String> typeNames = annotation.getReferences().keySet();
               refs.addInheritedTypes(typeNames);
               qualifiedTypeReferences.addAll(typeNames);
            }
         }
      });

      final Map<String, RequiredPackages> result = new HashMap<String, RequiredPackages>(packageToRefsMap.size());
      for (Entry<String, RequiredPackageBuilder> entry : packageToRefsMap.entrySet())
      {
         result.put(entry.getKey(), entry.getValue().build());
      }
      return result;
   }

   private static void collectReferencedTypes(final RequiredPackageBuilder refs, final JavaFile jFile,
      final Set<String> qualifiedTypeReferences)
   {
      jFile.accept(new TypeReferenceVisitor()
      {
         @Override
         protected void foundTypeReference(JavaType jType, Set<String> qualifiedNames)
         {
            qualifiedTypeReferences.addAll(qualifiedNames);
            refs.addInvokedTypes(qualifiedNames);
         }
      });
   }

   private static JavaType resolveJavaType(List<JavaResourceBundle> classPath, String name)
   {
      for (JavaResourceBundle jBundle : classPath)
      {
         final JavaType jType = resolveJavaType(jBundle, name);
         if (jType != null)
         {
            return jType;
         }
      }
      return null;
   }

   private static JavaType resolveJavaType(JavaResourceBundle jBundle, String name)
   {
      final String[] segments = name.replace('$', '.').split("\\.");
      for (JavaResourcesRoot javaResourcesRoot : jBundle.getResourcesRoots())
      {
         JavaResourceDirectory jPackage = javaResourcesRoot;
         JavaType type = null;
         for (int i = 0; i < segments.length; i++)
         {
            final String segment = segments[i];
            final JavaPackage nextPackage = jPackage.getPackage(segment);
            if (nextPackage != null)
            {
               jPackage = nextPackage;
               continue;
            }

            type = jPackage.getType(segment);
            if (type != null)
            {
               return type;
            }
         }
      }
      return null;
   }
}
