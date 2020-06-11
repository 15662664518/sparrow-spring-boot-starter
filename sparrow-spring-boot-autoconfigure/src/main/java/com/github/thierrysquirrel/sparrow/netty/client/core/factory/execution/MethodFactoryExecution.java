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
package com.github.thierrysquirrel.sparrow.netty.client.core.factory.execution;

import com.github.thierrysquirrel.sparrow.error.SparrowException;
import com.github.thierrysquirrel.sparrow.netty.client.core.factory.MethodFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.utils.SerializerUtils;

import java.lang.reflect.Method;

/**
 * ClassName: MethodFactoryExecution
 * Description:
 * date: 2020/6/11 7:10
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class MethodFactoryExecution {
    private final Object bean;
    private final Method method;

    public MethodFactoryExecution(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    public void execution(byte[] message) throws SparrowException {
        Class<?> methodParameter = MethodFactory.getMethodParameterType (method);
        Object methodParameterBean = SerializerUtils.deSerialize (message, methodParameter);
        try {
            method.invoke (bean, methodParameterBean);
        } catch (Exception e) {
            throw new SparrowException ("MethodInvoke Error", e);
        }
    }
}
