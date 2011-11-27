/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.utils;

import java.io.ByteArrayOutputStream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.junit.Test;
import org.sourcepit.osgifyme.core.java.JavaModel;
import org.sourcepit.osgifyme.core.java.JavaModelFactory;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaPackageRoot;
import org.sourcepit.osgifyme.core.java.JavaProject;

public class ModelTest
{
   @Test
   public void testModel() throws Exception
   {
      JavaModel javaModel = JavaModelFactory.eINSTANCE.createJavaModel();

      JavaProject prj = JavaModelFactory.eINSTANCE.createJavaProject();
      javaModel.getProjects().add(prj);

      JavaPackageRoot packageRoot = prj.getPackageRoot("src", true);

      EList<JavaPackage> eList3 = packageRoot.getRootPackages();

      JavaPackage pkg = JavaModelFactory.eINSTANCE.createJavaPackage();
      pkg.setSimpleName("java");
      eList3.add(pkg);

      JavaPackage pkg2 = JavaModelFactory.eINSTANCE.createJavaPackage();
      pkg2.setSimpleName("lang");

      pkg.getPackages().add(pkg2);

      Resource res = new XMLResourceImpl();
      res.getContents().add(javaModel);

      ByteArrayOutputStream out = new ByteArrayOutputStream();

      res.save(out, null);

      System.out.println(out.toString());
   }
}
