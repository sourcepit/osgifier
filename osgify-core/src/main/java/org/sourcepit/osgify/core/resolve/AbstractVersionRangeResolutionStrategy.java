/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.common.utils.priority.AbstractPriorized;
import org.sourcepit.osgify.core.model.context.BundleReference;

public abstract class AbstractVersionRangeResolutionStrategy extends AbstractPriorized
{
   public abstract VersionRange resolveVersionRange(BundleReference bundleReference);
}