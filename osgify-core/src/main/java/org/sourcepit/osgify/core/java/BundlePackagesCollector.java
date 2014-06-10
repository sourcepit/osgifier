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
import org.sourcepit.osgify.core.java.TypeReferenceVisitor;
import org.sourcepit.osgify.core.java.BundlePackages;
import org.sourcepit.osgify.core.java.PackageReferences;
import org.sourcepit.osgify.core.java.PackageReferencesBuilder;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.java.JavaFile;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.model.java.JavaType;
import org.sourcepit.osgify.core.model.java.Resource;
import org.sourcepit.osgify.core.model.java.ResourceVisitor;

public class BundlePackagesCollector
{
   public BundlePackages collect(BundleCandidate bundle)
   {
      final Map<String, PackageReferences> packageToRefsMap = collectPackageReferences(bundle);

      final List<String> bundlePackages = new ArrayList<String>(packageToRefsMap.size());
      final PackageReferencesBuilder bundleRefs = new PackageReferencesBuilder();
      for (Entry<String, PackageReferences> entry : packageToRefsMap.entrySet())
      {
         final String packageName = entry.getKey();
         bundlePackages.add(packageName);

         final PackageReferences refs = entry.getValue();
         bundleRefs.addInheritedPackages(refs.getInherited());
         bundleRefs.addInvokedPackages(refs.getInvoked());
      }

      sort(bundlePackages);

      return new BundlePackages(unmodifiableCollection(bundlePackages), bundleRefs.build(),
         Collections.unmodifiableMap(packageToRefsMap));
   }

   private static Map<String, PackageReferences> collectPackageReferences(final BundleCandidate bundle)
   {
      final Map<String, PackageReferencesBuilder> packageToRefsMap = new HashMap<String, PackageReferencesBuilder>();

      final JavaResourceBundle jBundle = bundle.getContent();

      jBundle.accept(new ResourceVisitor()
      {
         @Override
         public boolean visit(Resource resource)
         {
            if (resource instanceof JavaResourcesRoot)
            {
               packageToRefsMap.put("", new PackageReferencesBuilder());
               return true;
            }

            if (resource instanceof JavaPackage)
            {
               packageToRefsMap.put(((JavaPackage) resource).getQualifiedName(), new PackageReferencesBuilder());
               return true;
            }

            if (resource instanceof JavaFile)
            {
               final JavaFile jFile = (JavaFile) resource;
               final JavaPackage jPackage = jFile.getParentPackage();
               if (jPackage != null)
               {
                  final PackageReferencesBuilder refs = packageToRefsMap.get(jPackage.getQualifiedName());
                  visit(refs, jPackage, jFile, jFile.getType());
               }
            }

            return false;
         }

         private void visit(PackageReferencesBuilder refs, JavaPackage jPackage, JavaFile jFile, JavaType jType)
         {
            final Set<String> qualifiedTypeReferences = new HashSet<String>();
            collectReferencedTypes(refs, bundle, jFile, qualifiedTypeReferences);

            // HACK must be recursive
            addSuperTypeAndInterfaces(refs, jType, qualifiedTypeReferences);
            for (String qualifiedType : new HashSet<String>(qualifiedTypeReferences))
            {
               JavaType resolveJavaType = resolveJavaType(bundle, qualifiedType);
               if (resolveJavaType != null)
               {
                  addSuperTypeAndInterfaces(refs, resolveJavaType, qualifiedTypeReferences);
               }
            }
         }

         private void addSuperTypeAndInterfaces(final PackageReferencesBuilder refs, JavaType jType,
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

      final Map<String, PackageReferences> result = new HashMap<String, PackageReferences>(packageToRefsMap.size());
      for (Entry<String, PackageReferencesBuilder> entry : packageToRefsMap.entrySet())
      {
         result.put(entry.getKey(), entry.getValue().build());
      }
      return result;
   }

   private static void collectReferencedTypes(final PackageReferencesBuilder refs, final BundleCandidate bundle,
      final JavaFile jFile, final Set<String> qualifiedTypeReferences)
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

   private static JavaType resolveJavaType(BundleCandidate bundle, String name)
   {
      JavaResourceBundle jBundle = bundle.getContent();
      JavaType jType = resolveJavaType(jBundle, name);
      if (jType != null)
      {
         return jType;
      }
      for (BundleReference reference : bundle.getDependencies())
      {
         BundleCandidate target = reference.getTarget();
         if (target != null && target.getContent() != null)
         {
            jType = resolveJavaType(target.getContent(), name);
            if (jType != null)
            {
               return jType;
            }
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
