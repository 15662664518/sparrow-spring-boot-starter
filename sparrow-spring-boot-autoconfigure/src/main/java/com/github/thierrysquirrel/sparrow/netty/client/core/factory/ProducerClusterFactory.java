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
package com.github.thierrysquirrel.sparrow.netty.client.core.factory;

import com.github.thierrysquirrel.sparrow.server.common.netty.client.init.ProducerInit;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.SparrowRequestContextBuilder;

/**
 * ClassName: ProducerClusterFactory
 * Description:
 * date: 2020/6/11 7:01
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ProducerClusterFactory {
    private ProducerClusterFactory() {
    }

    public static void postMessage(String clusterUrl, String topic, byte[] message) throws InterruptedException {
        ProducerInit producerInit = ProducerClusterCacheFactory.getProducerInit (clusterUrl);
        producerInit.init ();
        SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderPostMessage (topic, message);
        producerInit.getChannel ().writeAndFlush (sparrowRequestContext);
    }
}
