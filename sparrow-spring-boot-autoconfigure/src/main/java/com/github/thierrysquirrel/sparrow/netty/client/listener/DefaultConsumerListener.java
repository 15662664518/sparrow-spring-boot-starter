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
package com.github.thierrysquirrel.sparrow.netty.client.listener;

import com.github.thierrysquirrel.sparrow.error.SparrowException;
import com.github.thierrysquirrel.sparrow.netty.client.core.factory.execution.MethodFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.core.constant.ConsumerState;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.listener.ConsumerListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: DefaultConsumerListener
 * Description:
 * date: 2020/6/11 7:06
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
@Slf4j
public class DefaultConsumerListener implements ConsumerListener {
    private MethodFactoryExecution methodFactoryExecution;

    public DefaultConsumerListener(MethodFactoryExecution methodFactoryExecution) {
        this.methodFactoryExecution = methodFactoryExecution;
    }

    @Override
    public ConsumerState consumer(byte[] message) {
        try {
            methodFactoryExecution.execution (message);
            return ConsumerState.SUCCESS;
        } catch (SparrowException e) {
            log.error ("Consumer Error", e);
        }
        return ConsumerState.FAILED;
    }
}
