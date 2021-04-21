/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file:
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 * Other contributors include Andrew Wright, Jeffrey Hayes,
 * Pat Fisher, Mike Judd.
 */

package test.java.util.concurrent.tck;

import java.util.concurrent.ExecutionException;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ExecutionExceptionTest extends JSR166TestCase {
    public static void main(String[] args) {
        main(suite(), args);
    }
    public static Test suite() {
        return new TestSuite(ExecutionExceptionTest.class);
    }

    // Adding derived class to be able to test the protected constructors
    private class TestExecutionException extends ExecutionException {
        public TestExecutionException() {
            super();
        }
        public TestExecutionException(String message) {
            super(message);
        }
    }

    /**
     * constructor creates exception without any details
     */
    public void testConstructNoMessage() {
        ExecutionException exception = new TestExecutionException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    /**
     * constructor creates exception with detail message
     */
    public void testConstructWithMessage() {
        ExecutionException exception = new TestExecutionException("test");
        assertEquals("test", exception.getMessage());
        assertNull(exception.getCause());
    }

    /**
     * constructor creates exception with detail message and cause
     */
    public void testConstructWithMessageAndCause() {
        Throwable cause = new Exception();
        ExecutionException exception = new ExecutionException("test", cause);
        assertEquals("test", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

}
