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
package com.github.thierrysquirrel.sparrow.init.core.factory.execution;

import com.github.thierrysquirrel.sparrow.annotation.MessageListener;
import com.github.thierrysquirrel.sparrow.annotation.SparrowListener;
import com.github.thierrysquirrel.sparrow.autoconfigure.SparrowProperties;
import com.github.thierrysquirrel.sparrow.core.utils.AnnotatedMethodsUtils;
import com.github.thierrysquirrel.sparrow.init.core.factory.SparrowConsumerInitFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.core.factory.ConsumerClusterCacheFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * ClassName: SparrowConsumerInitFactoryExecution 
 * Description: 
 * date: 2020/6/11 8:14
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class SparrowConsumerInitFactoryExecution {
    private SparrowConsumerInitFactoryExecution() {
    }
    public static void init(SparrowProperties sparrowProperties,ApplicationContext applicationContext){
        String sparrowServerUrl = sparrowProperties.getSparrowServerUrl ();
        applicationContext.getBeansWithAnnotation (SparrowListener.class).forEach ((beanName, bean) ->
                AnnotatedMethodsUtils.getMethodAndAnnotation (bean, MessageListener.class).
                        forEach ((method, messageListener) -> SparrowConsumerInitFactory.putConsumerInit (bean, method, messageListener, sparrowServerUrl)));
        if (ObjectUtils.isEmpty (ConsumerClusterCacheFactory.getAllConsumerRequest ())) {
            return;
        }
        SparrowConsumerInitFactory.consumerPing ();
        SparrowConsumerInitFactory.consumerPull (sparrowServerUrl);

    }
}
