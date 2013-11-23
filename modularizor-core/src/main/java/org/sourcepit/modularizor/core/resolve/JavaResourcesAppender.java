/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.resolve;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.emf.ecore.EObject;
import org.sourcepit.modularizor.moduleworlds.AbstractModule;
import org.sourcepit.modularizor.moduleworlds.AssembledModule;
import org.sourcepit.modularizor.moduleworlds.ExplodedModule;
import org.sourcepit.modularizor.moduleworlds.ModuleWorld;
import org.sourcepit.modularizor.moduleworlds.util.ModuleWorldsSwitch;

@Named
public class JavaResourcesAppender
{
   private final ModuleJavaResourcesScanner scanner;

   @Inject
   public JavaResourcesAppender(ModuleJavaResourcesScanner scanner)
   {
      this.scanner = scanner;
   }

   public void appendJavaResources(ModuleWorld world)
   {
      appendJavaResources(world.getModules());
   }

   public void appendJavaResources(List<AbstractModule> modules)
   {
      final ScannerTaskFactory taskFactory = new ScannerTaskFactory(scanner);
      final List<ScannerTask> tasks = new ArrayList<ScannerTask>(modules.size());
      for (AbstractModule module : modules)
      {
         tasks.add(taskFactory.createScannerTask(module));
      }
      executeScannerTasks(tasks);
   }

   private static class ScannerTaskFactory extends ModuleWorldsSwitch<ScannerTask>
   {
      private final ModuleJavaResourcesScanner scanner;

      public ScannerTask createScannerTask(AbstractModule module)
      {
         return doSwitch(module);
      }

      public ScannerTaskFactory(ModuleJavaResourcesScanner scanner)
      {
         this.scanner = scanner;
      }

      @Override
      public ScannerTask caseExplodedModule(final ExplodedModule module)
      {
         return new ScannerTask(scanner, module);
      }

      @Override
      public ScannerTask caseAssembledModule(AssembledModule module)
      {
         return new ScannerTask(scanner, module);
      }

      @Override
      public ScannerTask defaultCase(EObject object)
      {
         throw new IllegalStateException();
      }
   }

   private static class ScannerTask implements Runnable, Comparable<ScannerTask>
   {
      private final ModuleJavaResourcesScanner scanner;

      private final AssembledModule assembledModule;

      private final ExplodedModule explodedModule;

      @Inject
      public ScannerTask(ModuleJavaResourcesScanner scanner, AssembledModule assembledModule)
      {
         this.scanner = scanner;
         this.assembledModule = assembledModule;
         this.explodedModule = null;
      }

      @Inject
      public ScannerTask(ModuleJavaResourcesScanner scanner, ExplodedModule explodedModule)
      {
         this.scanner = scanner;
         this.assembledModule = null;
         this.explodedModule = explodedModule;
      }

      @Override
      public void run()
      {
         if (assembledModule == null)
         {
            scanner.scan(explodedModule);
         }
         else
         {
            scanner.scan(assembledModule);
         }
      }

      protected File getFileOrDirectory()
      {
         if (assembledModule == null)
         {
            return explodedModule.getDirectory();
         }
         return assembledModule.getFile();
      }


      @Override
      public int compareTo(ScannerTask o)
      {
         final File f1 = getFileOrDirectory();
         final File f2 = o.getFileOrDirectory();
         if (f1.isDirectory())
         {
            return f2.isDirectory() ? 0 : -1;
         }
         return (int) -(f1.length() - f2.length());
      }
   }

   private void executeScannerTasks(List<ScannerTask> tasks)
   {
      Collections.sort(tasks);

      ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
      for (ScannerTask task : tasks)
      {
         executor.execute(task);
      }

      executor.shutdown();

      BlockingQueue<Runnable> queue = executor.getQueue();

      Runnable poll = queue.poll();
      while (poll != null)
      {
         poll.run();
         poll = queue.poll();
      }

      try
      {
         executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
      }
      catch (InterruptedException e)
      {
      }
   }

}
