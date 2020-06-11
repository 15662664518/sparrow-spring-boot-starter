/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.thierrysquirrel.sparrow.netty.client.thread.execution;

import com.github.thierrysquirrel.sparrow.netty.client.thread.AbstractConsumerPullThread;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.core.constant.ConsumerConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.core.factory.ConsumerClusterFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: ConsumerPullThreadExecution
 * Description:
 * date: 2020/6/11 8:08
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class ConsumerPullThreadExecution extends AbstractConsumerPullThread {
    public ConsumerPullThreadExecution(String topic, String clusterUrl) {
        super (topic, clusterUrl);
    }

    @Override
    protected void consumerPull(String topic, String clusterUrl) {
        try {
            ConsumerClusterFactory.pull (topic, clusterUrl, ConsumerConstant.INIT_PULL_INDEX, ConsumerConstant.PULL_SIZE);
        } catch (Exception e) {
            log.error ("ConsumerPull Error", e);
        }
    }
}
