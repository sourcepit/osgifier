/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import java.lang.IllegalArgumentException;

import org.eclipse.emf.ecore.EObject;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.common.modeling.utils.EcoreUtils;
import org.sourcepit.common.modeling.utils.EcoreUtils.RunnableWithEObject;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class ResourceVisitorTest
{

   @Test
   public void testNull()
   {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getResource(), new RunnableWithEObject()
      {
         public void run(EObject eObject)
         {
            testNull((Resource) eObject);
         }
      });
   }

   private void testNull(Resource resource)
   {
      try
      {
         resource.accept(null);
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }
   }

   @Test
   public void testThis()
   {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getResource(), new RunnableWithEObject()
      {
         public void run(EObject eObject)
         {
            testThis((Resource) eObject);
         }
      });
   }

   private void testThis(final Resource resource)
   {
      resource.accept(new ResourceVisitor()
      {
         public boolean visit(Resource r)
         {
            assertThat(r, IsEqual.equalTo(resource));
            return false;
         }
      });
   }

   @Test
   public void testDir()
   {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getDirectory(), new RunnableWithEObject()
      {
         public void run(EObject eObject)
         {
            testDir((Directory) eObject);
         }
      });
   }

   private void testDir(Directory dir)
   {
      final Directory dir2 = JavaModelFactory.eINSTANCE.createDirectory();
      final File file = JavaModelFactory.eINSTANCE.createFile();
      final File file2 = JavaModelFactory.eINSTANCE.createFile();

      dir.getResources().add(dir2);
      dir.getResources().add(file);
      dir2.getResources().add(file2);

      final List<Resource> visited = new ArrayList<Resource>();
      dir.accept(new ResourceVisitor()
      {
         public boolean visit(Resource resource)
         {
            visited.add(resource);
            return false;
         }
      });

      assertThat(visited.size(), Is.is(1));
      assertThat(visited.get(0), IsEqual.equalTo((Resource) dir));

      visited.clear();

      dir.accept(new ResourceVisitor()
      {
         public boolean visit(Resource resource)
         {
            visited.add(resource);
            return resource != dir2;
         }
      });

      assertThat(visited.size(), Is.is(3));
      assertThat(visited.get(0), IsEqual.equalTo((Resource) dir));
      assertThat(visited.get(1), IsEqual.equalTo((Resource) dir2));
      assertThat(visited.get(2), IsEqual.equalTo((Resource) file));

      visited.clear();

      dir.accept(new ResourceVisitor()
      {
         public boolean visit(Resource resource)
         {
            visited.add(resource);
            return true;
         }
      });

      assertThat(visited.size(), Is.is(4));
      assertThat(visited.get(0), IsEqual.equalTo((Resource) dir));
      assertThat(visited.get(1), IsEqual.equalTo((Resource) dir2));
      assertThat(visited.get(2), IsEqual.equalTo((Resource) file2));
      assertThat(visited.get(3), IsEqual.equalTo((Resource) file));
   }
}
