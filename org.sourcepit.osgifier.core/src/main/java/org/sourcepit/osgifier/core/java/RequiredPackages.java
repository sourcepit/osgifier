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

package org.sourcepit.osgifier.core.java;

import java.util.Collection;

public class RequiredPackages {
   private final Collection<String> inherited;
   private final Collection<String> invoked;
   private final Collection<String> all;

   RequiredPackages(Collection<String> inherited, Collection<String> invoked, Collection<String> all) {
      this.inherited = inherited;
      this.invoked = invoked;
      this.all = all;
   }

   public Collection<String> getInherited() {
      return inherited;
   }

   public Collection<String> getInvoked() {
      return invoked;
   }

   public Collection<String> getAll() {
      return all;
   }

}
