package com.schneewittchen.rosandroid.utility.ros.exception;

/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/**
 * @author damonkohler@google.com (Damon Kohler)
 */
public class RosRuntimeException extends RuntimeException {

    public RosRuntimeException(final Throwable throwable) {
        super(throwable);
    }

    public RosRuntimeException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public RosRuntimeException(final String message) {
        super(message);
    }
}
