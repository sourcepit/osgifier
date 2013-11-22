/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.p2;

import org.eclipse.sisu.equinox.launching.internal.P2ApplicationLauncher;

public interface P2ApplicationLauncherFactory
{
   P2ApplicationLauncher createP2ApplicationLauncher();
}
