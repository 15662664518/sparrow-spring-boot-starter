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

import com.github.thierrysquirrel.sparrow.netty.client.core.factory.ProducerClusterFactory;
import com.github.thierrysquirrel.sparrow.netty.client.thread.AbstractAsyncProducerThread;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: AsyncProducerThreadExecution
 * Description:
 * date: 2020/6/11 8:33
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class AsyncProducerThreadExecution extends AbstractAsyncProducerThread {
    public AsyncProducerThreadExecution(String clusterUrl, String topic, byte[] message) {
        super (clusterUrl, topic, message);
    }

    @Override
    protected void asyncProducer(String clusterUrl, String topic, byte[] message) {
        try {
            ProducerClusterFactory.postMessage (clusterUrl, topic, message);
        } catch (Exception e) {
            log.error ("AsyncProducer Error", e);
        }
    }
}
