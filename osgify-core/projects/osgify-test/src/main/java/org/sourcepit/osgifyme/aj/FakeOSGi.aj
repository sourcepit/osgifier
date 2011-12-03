/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.aj;

import javax.xml.namespace.QName;

import org.sourcepit.osgifyme.Main;

public aspect FakeOSGi
{
   pointcut isOSGiAvailable(Main main) : target(main) && execution(boolean isOSGiAvailable());

   boolean around(Main main) : isOSGiAvailable(main){
      // force import of a class from stax api jar
      QName name = new QName(FakeOSGi.class.getName(), "isOSGiAvailable");
      System.out.println(name);
      return true;
   }
}
