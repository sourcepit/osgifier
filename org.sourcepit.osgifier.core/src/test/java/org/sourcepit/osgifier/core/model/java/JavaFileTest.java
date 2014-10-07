/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.core.model.java;

import static org.junit.Assert.assertThat;

import org.eclipse.emf.ecore.EObject;
import org.hamcrest.core.IsSame;
import org.junit.Test;
import org.sourcepit.common.modeling.utils.EcoreUtils;
import org.sourcepit.common.modeling.utils.EcoreUtils.RunnableWithEObject;
import org.sourcepit.osgifier.core.model.java.JavaFile;
import org.sourcepit.osgifier.core.model.java.JavaModelPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaFileTest
{

   @Test
   public void testGetResourceBundle()
   {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceBundle(), new RunnableWithEObject()
      {
         public void run(EObject eObject)
         {
            testGetResourceBundle((JavaResourceBundle) eObject);
         }
      });
   }

   private void testGetResourceBundle(JavaResourceBundle jBundle)
   {
      JavaFile javaFile = jBundle.getType("/", "org.sourcepit", "Foo", true).getFile();
      assertThat(javaFile.getResourceBundle(), IsSame.sameInstance(jBundle));
   }

   @Test
   public void testGetResourceRoot()
   {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceBundle(), new RunnableWithEObject()
      {
         public void run(EObject eObject)
         {
            testGetResourceRoot((JavaResourceBundle) eObject);
         }
      });
   }

   private void testGetResourceRoot(JavaResourceBundle jBundle)
   {
      JavaFile jFile = jBundle.getType("/", "org.sourcepit", "Foo", true).getFile();
      assertThat(jFile.getResourcesRoot(), IsSame.sameInstance(jBundle.getResourcesRoot("/")));
   }

}
