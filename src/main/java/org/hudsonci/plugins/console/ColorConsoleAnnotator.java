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

import hudson.MarkupText;
import hudson.console.ConsoleAnnotator;
import hudson.console.ConsoleAnnotatorFactory;

import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Adds color to build console logs.
 * 
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 */
public class ColorConsoleAnnotator<T>
    extends ConsoleAnnotator<T>
{
    //
    // TODO: Expose configuration of colorization
    //

    @Override
    public ConsoleAnnotator<T> annotate( final Object context, final MarkupText text )
    {
        // context is ignored
        assert text != null;

        String str = text.getText();
        if ( str.contains( "BUILD SUCCESS" ) || str.startsWith( "Finished: SUCCESS" ) )
        {
            text.addMarkup( 0, text.length(), "<strong><font color='green'>", "</font></strong>" );
        }
        else if ( str.contains( "BUILD FAILURE" ) || str.startsWith( "Finished: FAILURE" )
            || str.startsWith( "Finished: ABORTED" ) )
        {
            text.addMarkup( 0, text.length(), "<strong><font color='red'>", "</font></strong>" );
        }
        else if ( str.contains( "ERROR" ) || str.contains( "FAILURE" ) || str.contains( "FAILED" ) )
        {
            text.addMarkup( 0, text.length(), "<font color='red'>", "</font>" );
        }
        else if ( str.contains( "WARN" ) || str.contains( "Finished: UNSTABLE" ) )
        {
            text.addMarkup( 0, text.length(), "<font color='orange'>", "</font>" );
        }
        else if ( str.contains( "SUCCESS" ) || str.contains( ".OK (" ) )
        {
            text.addMarkup( 0, text.length(), "<font color='green'>", "</font>" );
        }

        return this;
    }

    @Named
    @Singleton
    @Typed( ConsoleAnnotatorFactory.class )
    public static class Factory<T>
        extends ConsoleAnnotatorFactory<T>
    {
        private final Provider<ColorConsoleAnnotator<T>> provider;

        @Inject
        public Factory( final Provider<ColorConsoleAnnotator<T>> provider )
        {
            this.provider = checkNotNull( provider );
        }

        @Override
        public ConsoleAnnotator<T> newInstance( final Object context )
        {
            return provider.get();
        }
    }
}