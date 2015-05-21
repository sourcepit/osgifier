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
public class JsonUtilsTest {

   @Test
   public void testParse() throws IOException {
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
