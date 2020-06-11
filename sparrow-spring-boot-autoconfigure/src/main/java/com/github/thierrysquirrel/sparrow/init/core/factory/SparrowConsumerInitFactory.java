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
package com.github.thierrysquirrel.sparrow.init.core.factory;

import com.github.thierrysquirrel.sparrow.annotation.MessageListener;
import com.github.thierrysquirrel.sparrow.core.factory.ThreadPoolFactory;
import com.github.thierrysquirrel.sparrow.init.core.constant.SparrowConsumerConstant;
import com.github.thierrysquirrel.sparrow.netty.client.core.factory.execution.MethodFactoryExecution;
import com.github.thierrysquirrel.sparrow.netty.client.listener.DefaultConsumerListener;
import com.github.thierrysquirrel.sparrow.netty.client.thread.execution.ConsumerPingThreadExecution;
import com.github.thierrysquirrel.sparrow.netty.client.thread.execution.ConsumerPullThreadExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.core.factory.ConsumerClusterCacheFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.init.ConsumerRequest;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.execution.ThreadPoolFactoryExecution;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * ClassName: SparrowConsumerInitFactory
 * Description:
 * date: 2020/6/11 7:40
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowConsumerInitFactory {
    private SparrowConsumerInitFactory() {
    }

    public static void putConsumerInit(Object bean, Method method, MessageListener messageListener, String clusterUrl) {
        MethodFactoryExecution methodFactoryExecution = new MethodFactoryExecution (bean, method);
        DefaultConsumerListener defaultConsumerListener = new DefaultConsumerListener (methodFactoryExecution);
        String topic = messageListener.value ();
        ConsumerClusterCacheFactory.putConsumerInit (topic, defaultConsumerListener, clusterUrl);
    }

    public static void consumerPing() {
        List<ConsumerRequest> consumerRequestList = ConsumerClusterCacheFactory.getAllConsumerRequest ();
        ScheduledThreadPoolExecutor consumerPingThreadPool = ThreadPoolFactory.createConsumerPingThreadPool (consumerRequestList.size ());
        for (ConsumerRequest consumerRequest : consumerRequestList) {
            ConsumerPingThreadExecution consumerPingThreadExecution = new ConsumerPingThreadExecution (consumerRequest);
            ThreadPoolFactoryExecution.statsTimingThread (consumerPingThreadPool, consumerPingThreadExecution, SparrowConsumerConstant.PING_INTERVAL);
        }
    }

    public static void consumerPull(String clusterUrl) {
        Map<String, List<ConsumerRequest>> consumerClusterMap = ConsumerClusterCacheFactory.getConsumerClusterMap ();
        ScheduledThreadPoolExecutor consumerPullThreadPool = ThreadPoolFactory.createConsumerPullThreadPool (consumerClusterMap.size ());
        for (String topic : consumerClusterMap.keySet ()) {
            ConsumerPullThreadExecution consumerPullThreadExecution = new ConsumerPullThreadExecution (topic, clusterUrl);
            ThreadPoolFactoryExecution.statsTimingThread (consumerPullThreadPool, consumerPullThreadExecution, SparrowConsumerConstant.PULL_INTERVAL);
        }
    }


}
