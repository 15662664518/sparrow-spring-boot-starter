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

import com.github.thierrysquirrel.sparrow.netty.client.thread.AbstractConsumerPingThread;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.init.ConsumerRequest;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.SparrowRequestContextBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: ConsumerPingThreadExecution
 * Description:
 * date: 2020/6/11 7:51
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class ConsumerPingThreadExecution extends AbstractConsumerPingThread {


    public ConsumerPingThreadExecution(ConsumerRequest consumerRequest) {
        super (consumerRequest);
    }

    @Override
    protected void consumerPing(ConsumerRequest consumerRequest) {
        try {
            consumerRequest.getConsumerInit ().init ();
            String topic = consumerRequest.getTopic ();
            SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderPing (topic);
            consumerRequest.getConsumerInit ().getChannel ().writeAndFlush (sparrowRequestContext);
        } catch (Exception e) {
            log.error ("ConsumerPing Error", e);
        }
    }
}
