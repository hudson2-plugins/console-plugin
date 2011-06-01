/**
 * Sonatype Hudson Professional (TM)
 * Copyright (C) 2010-2011 Sonatype, Inc. All rights reserved.
 * Includes the third-party code listed at http://www.sonatype.com/products/hudson/attributions/.
 * "Sonatype" and "Sonatype Hudson Professional" are trademarks of Sonatype, Inc.
 * "Hudson" is a trademark of Oracle, Inc.
 */
package com.sonatype.matrix.plugins.console;

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
 * @since 0.2
 */
public class ColorConsoleAnnotator
    extends ConsoleAnnotator
{
    //
    // FIXME: Expose configuration of colorization muck
    //
    
    @Override
    public ConsoleAnnotator annotate(final Object context, final MarkupText text) {
        // context is ignored
        assert text != null;

        String str = text.getText();
        if (str.contains("BUILD SUCCESS") || str.startsWith("Finished: SUCCESS")) {
            text.addMarkup(0, text.length(), "<strong><font color='green'>", "</font></strong>");
        }
        else if (str.contains("BUILD FAILURE") || str.startsWith("Finished: FAILURE") || str.startsWith("Finished: ABORTED")) {
            text.addMarkup(0, text.length(), "<strong><font color='red'>", "</font></strong>");
        }
        else if (str.contains("ERROR") || str.contains("FAILURE") || str.contains("FAILED")) {
            text.addMarkup(0, text.length(), "<font color='red'>", "</font>");
        }
        else if (str.contains("WARN") || str.contains("Finished: UNSTABLE")) {
            text.addMarkup(0, text.length(), "<font color='orange'>", "</font>");
        }
        else if (str.contains("SUCCESS") || str.contains(".OK (")) {
            text.addMarkup(0, text.length(), "<font color='green'>", "</font>");
        }

        return this;
    }

    @Named
    @Singleton
    @Typed(ConsoleAnnotatorFactory.class)
    public static class Factory
        extends ConsoleAnnotatorFactory
    {
        private final Provider<ColorConsoleAnnotator> provider;

        @Inject
        public Factory(final Provider<ColorConsoleAnnotator> provider) {
            this.provider = checkNotNull(provider);
        }

        @Override
        public ConsoleAnnotator newInstance(final Object context) {
            return provider.get();
        }
    }
}