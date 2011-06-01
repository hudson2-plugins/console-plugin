/**
 * The MIT License
 *
 * Copyright (c) 2010-2011 Sonatype, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.hudsonci.plugins.console;

import static com.google.common.base.Preconditions.checkNotNull;
import hudson.model.Action;
import hudson.model.AbstractProject;

import org.hudsonci.utils.plugin.ui.JellyAccessible;

/**
 * Provides a link to the latest console on the project details page.
 * 
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 */
public class LatestConsoleAction
    implements Action
{
    private final AbstractProject<?, ?> project;

    public LatestConsoleAction( final AbstractProject<?, ?> project )
    {
        this.project = checkNotNull( project );
    }

    public AbstractProject<?, ?> getProject()
    {
        return project;
    }

    public String getIconFileName()
    {
        return null;
    }

    public String getDisplayName()
    {
        return null;
    }

    public String getUrlName()
    {
        return null;
    }

    @JellyAccessible
    public boolean isBuildAvailable()
    {
        return getProject().getLastBuild() != null;
    }
}
