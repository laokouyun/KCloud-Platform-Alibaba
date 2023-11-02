/*
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.common.trace.config.auto;

import io.micrometer.common.lang.NonNullApi;
import org.laokou.common.i18n.utils.StringUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static org.laokou.common.i18n.common.Constant.EMPTY;

/**
 * @author laokou
 */
@NonNullApi
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class ReactiveTraceAutoConfig implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return null;
    }

    private String getHeaderValue(ServerHttpRequest request, String paramName) {
        // 从header中获取
        String paramValue = request.getHeaders().getFirst(paramName);
        // 从参数中获取
        if (StringUtil.isEmpty(paramValue)) {
            paramValue = request.getQueryParams().getFirst(paramName);
        }
        return StringUtil.isEmpty(paramValue) ? EMPTY : paramValue.trim();
    }

}
