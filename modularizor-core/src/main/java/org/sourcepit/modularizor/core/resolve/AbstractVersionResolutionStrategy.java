/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.resolve;

import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.priority.AbstractPriorized;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.modularizor.core.model.context.BundleCandidate;

/**
 * @author Bernd
 */
public abstract class AbstractVersionResolutionStrategy extends AbstractPriorized
{
   public abstract Version resolveVersion(BundleCandidate bundleCandidate, PropertiesSource options);
}
