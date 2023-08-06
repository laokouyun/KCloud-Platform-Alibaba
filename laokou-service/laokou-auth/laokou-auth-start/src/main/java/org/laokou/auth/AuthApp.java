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
package org.laokou.auth;

import com.alibaba.nacos.common.tls.TlsSystemConfig;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.laokou.common.security.config.CustomOpaqueTokenIntrospector;
import org.laokou.common.security.config.OAuth2ResourceServerProperties;
import org.laokou.common.security.config.auto.OAuth2AuthorizationAutoConfig;
import org.laokou.common.security.config.auto.OAuth2ResourceServerAutoConfig;
import org.laokou.common.security.exception.handler.ForbiddenExceptionHandler;
import org.laokou.common.security.exception.handler.InvalidAuthenticationEntryPoint;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.laokou.common.core.constant.Constant.TRUE;

/**
 * @author laokou
 */
@SpringBootApplication(exclude = { OAuth2AuthorizationServerAutoConfiguration.class
		, OAuth2ResourceServerAutoConfig.class
		, CustomOpaqueTokenIntrospector.class
		, ForbiddenExceptionHandler.class
		, OAuth2ResourceServerProperties.class
		, InvalidAuthenticationEntryPoint.class
		, OAuth2AuthorizationAutoConfig.class })
@EnableConfigurationProperties
@EnableEncryptableProperties
@EnableAsync
@EnableDiscoveryClient
public class AuthApp {

	public static void main(String[] args) {
		// SpringSecurity 子线程读取父线程的上下文
		System.setProperty(SecurityContextHolder.SYSTEM_PROPERTY, SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		// https://github.com/alibaba/nacos/pull/3654
		// 请查看 HttpLoginProcessor
		System.setProperty(TlsSystemConfig.TLS_ENABLE, TRUE);
		System.setProperty(TlsSystemConfig.CLIENT_AUTH, TRUE);
		System.setProperty(TlsSystemConfig.CLIENT_TRUST_CERT, "tls/nacos.cer");
		new SpringApplicationBuilder(AuthApp.class).web(WebApplicationType.SERVLET).run(args);
	}

}
