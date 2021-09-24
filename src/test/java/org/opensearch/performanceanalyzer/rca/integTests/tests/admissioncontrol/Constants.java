/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 *
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */

/*
 * Copyright 2021 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.opensearch.performanceanalyzer.rca.integTests.tests.admissioncontrol;


import org.opensearch.performanceanalyzer.rca.integTests.framework.configs.Consts;

public class Constants {
    public static final long KB_TO_BYTES = 1024;
    public static final long MB_TO_BYTES = KB_TO_BYTES * 1024;
    public static final long GB_TO_BYTES = MB_TO_BYTES * 1024;
    public static final long HEAP_MAX_SIZE_IN_BYTE = 10 * GB_TO_BYTES;

    public static final String ADMISSION_CONTROL_RESOURCES_DIR =
            Consts.INTEG_TESTS_SRC_DIR + "./tests/admissioncontrol/resource/";
}
