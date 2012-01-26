/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.guice.bean.binders.SpaceModule;
import org.sonatype.guice.bean.binders.WireModule;
import org.sonatype.guice.bean.locators.MutableBeanLocator;
import org.sonatype.guice.bean.reflect.ClassSpace;
import org.sonatype.guice.bean.reflect.URLClassSpace;
import org.sonatype.guice.bean.scanners.ClassSpaceVisitor;
import org.sonatype.guice.bean.scanners.QualifiedTypeVisitor;
import org.sonatype.guice.bean.scanners.asm.FieldVisitor;
import org.sonatype.inject.BeanScanning;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.pyx4j.log4j.MavenLogAppender;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public abstract class AbstractInjectedMojo extends AbstractMojo implements Module
{
   @Inject
   private MutableBeanLocator locator;

   @Inject
   private org.codehaus.plexus.logging.Logger fooooo;

   @Inject
   private List<org.codehaus.plexus.logging.Logger> bar;

   @Inject
   private IFoo foo;

   @Inject
   private List<IFoo> foos;

   /** @component */
   public DefaultPlexusContainer plexus;

   private static final Logger LOG = LoggerFactory.getLogger(AbstractInjectedMojo.class);

   public final void execute() throws MojoExecutionException, MojoFailureException
   {
      MavenLogAppender.startPluginLog(this);
      LOG.info("Set slf4j for mojo");

      final SpaceModule spaceModule = new SpaceModule(newClassSpace(), BeanScanning.CACHE)
      {
         @Override
         protected ClassSpaceVisitor visitor(final Binder binder)
         {
            return new QualifiedTypeVisitorDecorator((QualifiedTypeVisitor) super.visitor(binder))
            {
               private Jsr330FieldBinder fieldBinder;

               @Override
               public void visit(ClassSpace _space)
               {
                  super.visit(_space);
                  fieldBinder = new Jsr330FieldBinder(binder, _space);
               }

               @Override
               public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
               {
                  return fieldBinder.visitField(access, name, desc, signature);
               }
            };
         }
      };

      try
      {
         // ClassLoader classLoader = getClass().getClassLoader();
         // final Injector injector = Guice.createInjector(new WireModule(new AbstractModule()
         // {
         // @Override
         // protected void configure()
         // {
         // // try
         // // {
         // // Class<?> qualifiedType = org.codehaus.plexus.logging.Logger.class;
         // // final com.google.inject.name.Named fullName = Names.named(qualifiedType.getName());
         // // bind(Key.get(Object.class, fullName)).toInstance(
         // // plexus.lookup(org.codehaus.plexus.logging.Logger.class));
         // // }
         // // catch (ComponentLookupException e)
         // // {
         // // e.printStackTrace();
         // // }
         //
         // requestInjection(AbstractInjectedMojo.this);
         // }
         // }, new SpaceModule(new URLClassSpace(classLoader), BeanScanning.CACHE)));


         final ClassSpace space = new URLClassSpace(getClass().getClassLoader());
         Guice.createInjector(new WireModule(new TestModule(), spaceModule)); // , new SpaceModule(space,
                                                                              // BeanScanning.CACHE)));

         doExecute();
      }
      finally
      {
         LOG.info("Unset slf4j for mojo");
         MavenLogAppender.endPluginLog(this);
      }
   }

   final class TestModule extends AbstractModule
   {
      @Override
      @SuppressWarnings({ "unchecked", "rawtypes" })
      protected void configure()
      {
         // install(AbstractInjectedMojo.this);

         com.google.inject.name.Named fullName = Names.named(MIFoo.class.getName());
         bind(Key.get(IFoo.class, fullName)).toInstance(new MIFoo());

         fullName = Names.named(Foo2.class.getName());
         bind(Key.get(IFoo.class, fullName)).toInstance(new Foo2());


         try
         {
            org.codehaus.plexus.logging.Logger lookup = plexus.lookup(org.codehaus.plexus.logging.Logger.class);
            
            Class<?> qualifiedType = lookup.getClass();
            fullName = Names.named(qualifiedType.getName());
            
            bind(Key.get(org.codehaus.plexus.logging.Logger.class, fullName)).toInstance(lookup);
         }
         catch (ComponentLookupException e)
         {
            e.printStackTrace();
         }

         requestInjection(AbstractInjectedMojo.this);
      }
   }

   protected abstract void doExecute() throws MojoExecutionException, MojoFailureException;

   protected URLClassSpace newClassSpace()
   {
      return new URLClassSpace(getClass().getClassLoader());
   }

   protected BeanScanning getScanning()
   {
      return BeanScanning.CACHE;
   }

   public void configure(Binder binder)
   { // override to set custom bindings
   }

   // ----------------------------------------------------------------------
   // Container lookup methods
   // ----------------------------------------------------------------------
   public final <T> T lookup(final Class<T> type)
   {
      return lookup(Key.get(type));
   }

   public final <T> T lookup(final Class<T> type, final String name)
   {
      return lookup(Key.get(type, Names.named(name)));
   }

   public final <T> T lookup(final Key<T> key)
   {
      final Iterator<? extends Entry<Annotation, T>> i = locator.locate(key).iterator();
      return i.hasNext() ? i.next().getValue() : null;
   }
}
