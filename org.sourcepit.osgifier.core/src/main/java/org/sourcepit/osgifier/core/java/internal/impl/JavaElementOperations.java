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

package org.sourcepit.osgifier.core.java.internal.impl;

import org.eclipse.emf.ecore.EObject;
import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.osgifier.core.model.java.JavaElement;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class JavaElementOperations {
   private JavaElementOperations() {
      super();
   }

   public static JavaResourceBundle getResourceBundle(@NotNull JavaElement jElement) {
      EObject current = jElement;
      while (current != null && !(current instanceof JavaResourceBundle)) {
         current = current.eContainer();
      }
      return (JavaResourceBundle) current;
   }
}
