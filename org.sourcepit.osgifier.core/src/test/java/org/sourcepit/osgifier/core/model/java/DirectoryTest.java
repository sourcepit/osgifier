/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.model.java;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.lang.IllegalArgumentException;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.osgifier.core.model.java.Directory;
import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.core.model.java.Resource;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class DirectoryTest
{
   @Test
   public void testGetDirectories()
   {
      Directory dir = JavaModelFactory.eINSTANCE.createDirectory();
      assertThat(dir.getDirectories().size(), Is.is(0));

      Directory dir2 = dir.getDirectory("Foo", true);
      assertThat(dir.getDirectories().size(), Is.is(1));

      assertThat(dir.getDirectory("Foo", true), Is.is(dir2));
      assertThat(dir.getDirectories().size(), Is.is(1));

      dir.getFile("Foo", true);
      assertThat(dir.getResources().size(), Is.is(2));
      assertThat(dir.getDirectories().size(), Is.is(1));

      Directory dir3 = dir.getDirectory("Z", true);
      assertThat(dir.getDirectories().size(), Is.is(2));

      assertThat(dir.getDirectories().get(0), Is.is(dir2));
      assertThat(dir.getDirectories().get(1), Is.is(dir3));

      try
      {
         dir.getDirectories().add(JavaModelFactory.eINSTANCE.createDirectory());
         fail("expected unmodifiable EList");
      }
      catch (UnsupportedOperationException e)
      {
      }
   }

   @Test
   public void testGetFiles()
   {
      Directory dir = JavaModelFactory.eINSTANCE.createDirectory();
      assertThat(dir.getFiles().size(), Is.is(0));

      File file = dir.getFile("Foo", true);
      assertThat(dir.getFiles().size(), Is.is(1));

      assertThat(dir.getFile("Foo", true), Is.is(file));
      assertThat(dir.getFiles().size(), Is.is(1));

      dir.getDirectory("Foo", true);
      assertThat(dir.getResources().size(), Is.is(2));
      assertThat(dir.getFiles().size(), Is.is(1));

      File file2 = dir.getFile("Z", true);
      assertThat(dir.getFiles().size(), Is.is(2));

      assertThat(dir.getFiles().get(0), Is.is(file));
      assertThat(dir.getFiles().get(1), Is.is(file2));

      try
      {
         dir.getFiles().add(JavaModelFactory.eINSTANCE.createFile());
         fail("expected unmodifiable EList");
      }
      catch (UnsupportedOperationException e)
      {
      }
   }

   @Test
   public void testGetFile()
   {
      Directory dir = JavaModelFactory.eINSTANCE.createDirectory();
      try
      {
         dir.getFile(null);
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }

      assertThat(dir.getFile("a", false), IsNull.nullValue());

      File a = dir.getFile("a", true);
      assertThat(a.getName(), IsEqual.equalTo("a"));
      assertThat(dir.getFile("a", false), IsNull.notNullValue());
      assertThat(dir.getFile("b", false), IsNull.nullValue());

      File b = dir.getFile("b", true);
      assertThat(b.getName(), IsEqual.equalTo("b"));
      assertThat(dir.getFile("a", false), IsNull.notNullValue());
      assertThat(dir.getFile("b", false), IsNull.notNullValue());

      File file = dir.getFile("foo/Bar.txt", true);
      assertThat(file.getName(), IsEqual.equalTo("Bar.txt"));
      assertThat(file.getParentDirectory().getParentDirectory(), Is.is(dir));
   }

   @Test
   public void testGetResource()
   {
      Directory dir = JavaModelFactory.eINSTANCE.createDirectory();
      try
      {
         dir.getResource(null);
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }

      assertThat(dir.getResource("a"), IsNull.nullValue());

      File a = dir.getFile("a", true);
      assertThat(a.getName(), IsEqual.equalTo("a"));
      assertThat(dir.getResource("a"), IsNull.notNullValue());
      assertThat(dir.getResource("b"), IsNull.nullValue());

      File b = dir.getFile("b", true);
      assertThat(b.getName(), IsEqual.equalTo("b"));
      assertThat(dir.getResource("a"), IsNull.notNullValue());
      assertThat(dir.getResource("b"), IsNull.notNullValue());

      File file = dir.getFile("foo/Bar.txt", true);
      assertThat(file.getName(), IsEqual.equalTo("Bar.txt"));
      assertThat(file.getParentDirectory().getParentDirectory(), Is.is(dir));

      Resource resource = dir.getResource("foo/Bar.txt");
      assertThat(resource, IsEqual.equalTo((Resource) file));
   }
}
