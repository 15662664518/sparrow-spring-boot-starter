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

import com.github.thierrysquirrel.sparrow.netty.client.listener.DefaultProducerListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.init.ProducerInit;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.SeparatorConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.LoadBalancingFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: ProducerClusterCacheFactory 
 * Description: 
 * date: 2020/6/11 7:00
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ProducerClusterCacheFactory {
    private static final ThreadLocal<List<ProducerInit>> PRODUCER_CLUSTER_CACHE = new InheritableThreadLocal<> ();
    private static final AtomicInteger PRODUCER_CLUSTER_INDEX = new AtomicInteger ();

    private ProducerClusterCacheFactory() {
    }

    public static ProducerInit getProducerInit(String clusterUrl) {
        List<ProducerInit> producerInitList = PRODUCER_CLUSTER_CACHE.get ();
        if (null == producerInitList) {
            producerInitList = putProducerInit (clusterUrl);
            PRODUCER_CLUSTER_CACHE.set (producerInitList);
        }
        Integer index = LoadBalancingFactory.getIndex (PRODUCER_CLUSTER_INDEX, producerInitList.size ());
        return producerInitList.get (index);

    }

    public static void remove() {
        PRODUCER_CLUSTER_CACHE.remove ();
    }

    private static List<ProducerInit> putProducerInit(String clusterUrl) {
        List<ProducerInit> producerInits = new ArrayList<> ();
        String[] split = clusterUrl.split (SeparatorConstant.URL_SEPARATOR);
        for (String url : split) {
            ProducerInit producerInit = new ProducerInit (url, new DefaultProducerListener ());
            producerInits.add (producerInit);
        }
        return producerInits;
    }
}
