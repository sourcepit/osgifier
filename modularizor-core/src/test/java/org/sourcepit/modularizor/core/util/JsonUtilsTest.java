/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.util;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JsonUtilsTest
{

   @Test
   public void testParse() throws IOException
   {
      StringWriter writer = new StringWriter();

      JsonWriter json = new JsonWriter(writer);
      json.beginArray();
      json.beginObject();
      json.name("type");
      json.value("jre");
      json.name("vendor");
      json.value("me");
      json.name("version");
      json.value("1337");
      json.name("platformPackages");
      json.beginArray();
      json.value("foo");
      json.value("bar");
      json.endArray();
      json.endObject();
      json.endArray();

      JsonArray jvms = JsonUtils.parse(new StringReader(writer.toString())).getAsJsonArray();
      assertThat(jvms.size(), Is.is(1));

      JsonObject jvm = (JsonObject) jvms.get(0);
      assertThat(jvm.get("type").getAsString(), IsEqual.equalTo("jre"));
      assertThat(jvm.get("vendor").getAsString(), IsEqual.equalTo("me"));
      assertThat(jvm.get("version").getAsString(), IsEqual.equalTo("1337"));

      JsonArray jPackages = (JsonArray) jvm.get("platformPackages");
      assertThat(jPackages.size(), Is.is(2));
      assertThat(jPackages.get(0).getAsString(), IsEqual.equalTo("foo"));
      assertThat(jPackages.get(1).getAsString(), IsEqual.equalTo("bar"));
   }

}
