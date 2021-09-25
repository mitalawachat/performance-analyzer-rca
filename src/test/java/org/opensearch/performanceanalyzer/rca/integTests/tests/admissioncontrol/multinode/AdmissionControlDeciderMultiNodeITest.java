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
 * Copyright 2020-2021 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

package org.opensearch.performanceanalyzer.rca.integTests.tests.admissioncontrol.multinode;

import static org.opensearch.performanceanalyzer.rca.integTests.tests.admissioncontrol.Constants.ADMISSION_CONTROL_RESOURCES_DIR;
import static org.opensearch.performanceanalyzer.rca.integTests.tests.admissioncontrol.Constants.HEAP_MAX_SIZE_IN_BYTE;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.opensearch.performanceanalyzer.metrics.AllMetrics;
import org.opensearch.performanceanalyzer.rca.framework.api.metrics.Heap_Max;
import org.opensearch.performanceanalyzer.rca.framework.api.metrics.Heap_Used;
import org.opensearch.performanceanalyzer.rca.integTests.framework.RcaItMarker;
import org.opensearch.performanceanalyzer.rca.integTests.framework.annotations.AClusterType;
import org.opensearch.performanceanalyzer.rca.integTests.framework.annotations.AErrorPatternIgnored;
import org.opensearch.performanceanalyzer.rca.integTests.framework.annotations.AExpect;
import org.opensearch.performanceanalyzer.rca.integTests.framework.annotations.AMetric;
import org.opensearch.performanceanalyzer.rca.integTests.framework.annotations.ARcaConf;
import org.opensearch.performanceanalyzer.rca.integTests.framework.annotations.ARcaGraph;
import org.opensearch.performanceanalyzer.rca.integTests.framework.annotations.ATable;
import org.opensearch.performanceanalyzer.rca.integTests.framework.annotations.ATuple;
import org.opensearch.performanceanalyzer.rca.integTests.framework.configs.ClusterType;
import org.opensearch.performanceanalyzer.rca.integTests.framework.configs.HostTag;
import org.opensearch.performanceanalyzer.rca.integTests.framework.runners.RcaItNotEncryptedRunner;
import org.opensearch.performanceanalyzer.rca.integTests.tests.admissioncontrol.validator.AdmissionControlDeciderValidator;
import org.opensearch.performanceanalyzer.rca.persistence.actions.PersistedAction;
import org.opensearch.performanceanalyzer.rca.store.OpenSearchAnalysisGraph;

@RunWith(RcaItNotEncryptedRunner.class)
@Category(RcaItMarker.class)
@AClusterType(ClusterType.MULTI_NODE_CO_LOCATED_MASTER)
@ARcaGraph(OpenSearchAnalysisGraph.class)
@ARcaConf(dataNode = ADMISSION_CONTROL_RESOURCES_DIR + "rca.conf")
@AMetric(
        name = Heap_Used.class,
        dimensionNames = {AllMetrics.HeapDimension.Constants.TYPE_VALUE},
        tables = {
            @ATable(
                    hostTag = {HostTag.DATA_0, HostTag.DATA_1},
                    tuple = {
                        @ATuple(
                                dimensionValues = AllMetrics.GCType.Constants.HEAP_VALUE,
                                sum = HEAP_MAX_SIZE_IN_BYTE * 0.85,
                                avg = HEAP_MAX_SIZE_IN_BYTE * 0.85,
                                min = HEAP_MAX_SIZE_IN_BYTE * 0.85,
                                max = HEAP_MAX_SIZE_IN_BYTE * 0.85),
                    })
        })
@AMetric(
        name = Heap_Max.class,
        dimensionNames = {AllMetrics.HeapDimension.Constants.TYPE_VALUE},
        tables = {
            @ATable(
                    hostTag = {HostTag.DATA_0, HostTag.DATA_1},
                    tuple = {
                        @ATuple(
                                dimensionValues = AllMetrics.GCType.Constants.HEAP_VALUE,
                                sum = HEAP_MAX_SIZE_IN_BYTE,
                                avg = HEAP_MAX_SIZE_IN_BYTE,
                                min = HEAP_MAX_SIZE_IN_BYTE,
                                max = HEAP_MAX_SIZE_IN_BYTE),
                    })
        })
public class AdmissionControlDeciderMultiNodeITest {

    @Test
    @AExpect(
            what = AExpect.Type.DB_QUERY,
            on = HostTag.ELECTED_MASTER,
            validator = AdmissionControlDeciderValidator.class,
            forRca = PersistedAction.class,
            timeoutSeconds = 10000)
    @AErrorPatternIgnored(
            pattern = "CacheUtil:getCacheMaxSize()",
            reason = "Cache related configs are expected to be missing in this test")
    @AErrorPatternIgnored(
            pattern = "AggregateMetric:gather()",
            reason = "Metrics is expected to be missing")
    @AErrorPatternIgnored(
            pattern = "SQLParsingUtil:readDataFromSqlResult()",
            reason = "Metrics is expected to be missing")
    @AErrorPatternIgnored(
            pattern = "OldGenRca:getMaxOldGenSizeOrDefault()",
            reason = "Metrics is expected to be missing")
    @AErrorPatternIgnored(
            pattern = "OldGenRca:getOldGenUsedOrDefault()",
            reason = "Metrics is expected to be missing")
    @AErrorPatternIgnored(
            pattern = "HighHeapUsageYoungGenRca:operate()",
            reason = "Metrics is expected to be missing")
    @AErrorPatternIgnored(
            pattern = "NodeConfigCacheReaderUtil:readQueueCapacity()",
            reason = "Metrics is expected to be missing")
    @AErrorPatternIgnored(
            pattern = "NodeConfigCacheReaderUtil:collectAndPublishMetric()",
            reason = "Metrics is expected to be missing")
    @AErrorPatternIgnored(
            pattern = "NodeConfigCollector:collectAndPublishMetric()",
            reason = "Metrics is expected to be missing")
    @AErrorPatternIgnored(
            pattern = "SubscribeResponseHandler:onError()",
            reason =
                    "A unit test expressly calls SubscribeResponseHandler#onError, which writes an error log")
    public void testAdmissionControlDecider() {}
}
