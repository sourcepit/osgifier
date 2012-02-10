/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.inject.Named;

import com.google.gson.JsonArray;
import com.google.gson.JsonStreamParser;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class JvmInfoReader
{
   public JsonArray read(InputStream inputStream)
   {
      try
      {
         return read(new InputStreamReader(inputStream, JvmInfoWriter.UTF_8));
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
   }

   public JsonArray read(Reader reader)
   {
      return (JsonArray) new JsonStreamParser(reader).next();
   }
}
