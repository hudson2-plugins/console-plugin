/**
 * Sonatype Hudson Professional (TM)
 * Copyright (C) 2010-2011 Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://www.sonatype.com/products/hudson/attributions/.
 * "Sonatype" and "Sonatype Hudson Professional" are trademarks of Sonatype, Inc.
 * "Hudson" is a trademark of Oracle, Inc.
 */
package com.sonatype.matrix.plugins.console;

import org.hudsonci.utils.plugin.ui.JellyAccessible;
import hudson.model.AbstractProject;
import hudson.model.Action;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides a link to the latest console on the project details page.
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 1.1
 */
public class LatestConsoleAction
    implements Action
{
    private final AbstractProject project;

    public LatestConsoleAction(final AbstractProject project) {
        this.project = checkNotNull(project);
    }

    public AbstractProject getProject() {
        return project;
    }

    @Override
    public String getIconFileName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getUrlName() {
        return null;
    }

    @JellyAccessible
    public boolean isBuildAvailable() {
        return getProject().getLastBuild() != null;
    }
}
