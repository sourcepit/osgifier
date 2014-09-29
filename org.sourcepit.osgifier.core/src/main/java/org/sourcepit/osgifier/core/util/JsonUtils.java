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

package org.sourcepit.osgifier.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class JsonUtils
{
   private JsonUtils()
   {
      super();
   }

   private static final String UTF_8 = "UTF-8";

   public static JsonElement parse(InputStream inputStream)
   {
      return parse(inputStream, UTF_8);
   }

   public static JsonElement parse(InputStream inputStream, String charset)
   {
      try
      {
         return parse(new InputStreamReader(inputStream, charset));
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
   }

   public static JsonElement parse(Reader reader)
   {
      return (JsonArray) new JsonStreamParser(reader).next();
   }

}
