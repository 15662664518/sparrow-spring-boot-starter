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
package com.github.thierrysquirrel.sparrow.netty.client.thread;

import lombok.Data;

/**
 * ClassName: AbstractAsyncProducerThread
 * Description:
 * date: 2020/6/11 8:31
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractAsyncProducerThread implements Runnable {
    private String clusterUrl;
    private String topic;
    private byte[] message;

    public AbstractAsyncProducerThread(String clusterUrl, String topic, byte[] message) {
        this.clusterUrl = clusterUrl;
        this.topic = topic;
        this.message = message;
    }

    /**
     * asyncProducer
     *
     * @param clusterUrl clusterUrl
     * @param topic      topic
     * @param message    message
     */
    protected abstract void asyncProducer(String clusterUrl, String topic, byte[] message);

    @Override
    public void run() {
        asyncProducer (this.clusterUrl, this.topic, this.message);
    }
}
