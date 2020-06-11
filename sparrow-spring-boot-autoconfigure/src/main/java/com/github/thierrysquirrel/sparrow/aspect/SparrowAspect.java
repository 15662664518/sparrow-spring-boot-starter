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
package com.github.thierrysquirrel.sparrow.aspect;

import com.github.thierrysquirrel.sparrow.annotation.SparrowAsyncProducer;
import com.github.thierrysquirrel.sparrow.annotation.SparrowProducer;
import com.github.thierrysquirrel.sparrow.aspect.core.factory.execution.SparrowAspectFactoryExecution;
import com.github.thierrysquirrel.sparrow.autoconfigure.SparrowProperties;
import com.github.thierrysquirrel.sparrow.core.utils.AspectUtils;
import com.github.thierrysquirrel.sparrow.error.SparrowException;
import com.github.thierrysquirrel.sparrow.template.AdministrationTemplate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Resource;

/**
 * ClassName: SparrowAspect
 * Description:
 * date: 2020/6/11 8:20
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Aspect
@Data
@Slf4j
public class SparrowAspect {
    @Resource
    private AdministrationTemplate administrationTemplate;
    @Resource
    private SparrowProperties sparrowProperties;

    @Pointcut("@annotation(com.github.thierrysquirrel.sparrow.annotation.SparrowProducer)")
    public void sparrowProducerPointcut() {
        log.debug ("Start SparrowProducerPointcut");
    }

    @Pointcut("@annotation(com.github.thierrysquirrel.sparrow.annotation.SparrowAsyncProducer)")
    public void sparrowAsyncProducer() {
        log.debug ("Start SparrowAsyncProducer");
    }

    @Around("sparrowProducerPointcut()")
    public Object sparrowProducerAround(ProceedingJoinPoint point) throws SparrowException {
        return SparrowAspectFactoryExecution.sparrowProducer (administrationTemplate,
                AspectUtils.getAnnotation (point, SparrowProducer.class),
                point);
    }

    @Around("sparrowAsyncProducer()")
    public Object sparrowAsyncProducerAround(ProceedingJoinPoint point) throws SparrowException {
        return SparrowAspectFactoryExecution.sparrowAsyncProducerAround (AspectUtils.getAnnotation (point, SparrowAsyncProducer.class),
                sparrowProperties.getSparrowServerUrl (),
                point);
    }
}
