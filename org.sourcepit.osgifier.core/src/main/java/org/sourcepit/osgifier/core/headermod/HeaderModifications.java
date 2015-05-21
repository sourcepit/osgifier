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

package org.sourcepit.osgifier.core.headermod;

import java.util.ArrayList;
import java.util.List;

public class HeaderModifications {
   private final List<SetHeaderModification> headers = new ArrayList<SetHeaderModification>(2);

   private final List<String> removals = new ArrayList<String>(2);

   public List<SetHeaderModification> getHeaders() {
      return headers;
   }

   public List<String> getRemovals() {
      return removals;
   }

}
