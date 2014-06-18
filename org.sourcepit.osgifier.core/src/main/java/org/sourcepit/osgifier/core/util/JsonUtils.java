/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
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
