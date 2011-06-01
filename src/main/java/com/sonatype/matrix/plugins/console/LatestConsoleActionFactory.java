/**
 * Sonatype Hudson Professional (TM)
 * Copyright (C) 2010-2011 Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://www.sonatype.com/products/hudson/attributions/.
 * "Sonatype" and "Sonatype Hudson Professional" are trademarks of Sonatype, Inc.
 * "Hudson" is a trademark of Oracle, Inc.
 */
package com.sonatype.matrix.plugins.console;

import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.TransientProjectActionFactory;

import javax.enterprise.inject.Typed;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.Collections;

/**
 * Attaches {@link LatestConsoleAction} to projects.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 1.1
 */
@Named
@Singleton
@Typed(TransientProjectActionFactory.class)
public class LatestConsoleActionFactory
    extends TransientProjectActionFactory
{
    @Override
    public Collection<? extends Action> createFor(final AbstractProject project) {
        return Collections.singleton(new LatestConsoleAction(project));
    }
}