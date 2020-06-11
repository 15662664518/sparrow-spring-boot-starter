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
package com.github.thierrysquirrel.sparrow.core.constant;

/**
 * ClassName: ThreadPoolConstant
 * Description:
 * date: 2020/6/11 7:56
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public final class ThreadPoolConstant {
    public static final String CONSUMER_PING = "ConsumerPing";
    public static final String CONSUMER_PULL = "ConsumerPull";

    public static final String ASYNC_PRODUCER = "AsyncProducer";
    public static final int ASYNC_PRODUCERS_CORE_POOL_SIZE = Runtime.getRuntime ().availableProcessors () * 2;
    public static final int ASYNC_PRODUCER_MAXIMUM_POOL_SIZE = Runtime.getRuntime ().availableProcessors () * 2;
    public static final int ASYNC_PRODUCER_KEEP_ALIVE_TIME = 0;

    private ThreadPoolConstant() {
    }
}
