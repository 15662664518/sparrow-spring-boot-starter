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
package com.github.thierrysquirrel.sparrow.core.factory;

import com.github.thierrysquirrel.sparrow.core.constant.ThreadPoolConstant;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * ClassName: ThreadPoolFactory
 * Description:
 * date: 2020/6/11 7:55
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ThreadPoolFactory {
    private ThreadPoolFactory() {
    }

    public static ScheduledThreadPoolExecutor createConsumerPingThreadPool(int corePoolSize) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder ()
                .setNameFormat (ThreadPoolConstant.CONSUMER_PING).build ();
        return new ScheduledThreadPoolExecutor (corePoolSize, threadFactory);
    }

    public static ScheduledThreadPoolExecutor createConsumerPullThreadPool(int corePoolSize) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder ()
                .setNameFormat (ThreadPoolConstant.CONSUMER_PULL).build ();
        return new ScheduledThreadPoolExecutor (corePoolSize, threadFactory);
    }

    public static ThreadPoolExecutor createAsyncProducerThreadPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder ()
                .setNameFormat (ThreadPoolConstant.ASYNC_PRODUCER).build ();
        return new ThreadPoolExecutor (ThreadPoolConstant.ASYNC_PRODUCERS_CORE_POOL_SIZE,
                ThreadPoolConstant.ASYNC_PRODUCER_MAXIMUM_POOL_SIZE,
                ThreadPoolConstant.ASYNC_PRODUCER_KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<> (),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy ()
        );
    }
}
