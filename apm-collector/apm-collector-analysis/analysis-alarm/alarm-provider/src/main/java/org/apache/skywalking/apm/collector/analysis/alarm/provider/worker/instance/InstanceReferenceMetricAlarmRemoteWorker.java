/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.apm.collector.analysis.alarm.provider.worker.instance;

import org.apache.skywalking.apm.collector.analysis.alarm.define.graph.AlarmWorkerIdDefine;
import org.apache.skywalking.apm.collector.analysis.worker.model.base.AbstractRemoteWorker;
import org.apache.skywalking.apm.collector.analysis.worker.model.base.AbstractRemoteWorkerProvider;
import org.apache.skywalking.apm.collector.analysis.worker.model.base.WorkerException;
import org.apache.skywalking.apm.collector.core.module.ModuleManager;
import org.apache.skywalking.apm.collector.remote.service.RemoteSenderService;
import org.apache.skywalking.apm.collector.remote.service.Selector;
import org.apache.skywalking.apm.collector.storage.table.alarm.InstanceReferenceAlarm;

/**
 * @author peng-yongsheng
 */
public class InstanceReferenceMetricAlarmRemoteWorker extends AbstractRemoteWorker<InstanceReferenceAlarm, InstanceReferenceAlarm> {

    public InstanceReferenceMetricAlarmRemoteWorker(ModuleManager moduleManager) {
        super(moduleManager);
    }

    @Override public int id() {
        return AlarmWorkerIdDefine.INSTANCE_REFERENCE_METRIC_ALARM_REMOTE_WORKER_ID;
    }

    @Override public Selector selector() {
        return Selector.HashCode;
    }

    @Override protected void onWork(InstanceReferenceAlarm message) throws WorkerException {
        onNext(message);
    }

    public static class Factory extends AbstractRemoteWorkerProvider<InstanceReferenceAlarm, InstanceReferenceAlarm, InstanceReferenceMetricAlarmRemoteWorker> {

        public Factory(ModuleManager moduleManager, RemoteSenderService remoteSenderService, int graphId) {
            super(moduleManager, remoteSenderService, graphId);
        }

        @Override public InstanceReferenceMetricAlarmRemoteWorker workerInstance(ModuleManager moduleManager) {
            return new InstanceReferenceMetricAlarmRemoteWorker(moduleManager);
        }
    }
}
