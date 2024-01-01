/*
 * Copyright (c) 2022 KCloud-Platform-Alibaba Author or Authors. All Rights Reserved.
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

package org.laokou.common.ratelimiter.driver;

import org.laokou.common.core.utils.RequestUtil;
import org.laokou.common.ratelimiter.driver.spi.KeyProvider;
import org.laokou.common.ratelimiter.enums.Type;

import java.util.ServiceLoader;

import static org.laokou.common.i18n.common.Constant.EMPTY;

/**
 * @author laokou
 */
public class KeyManager {

	public static String key(Type type) {
		ServiceLoader<KeyProvider> keyProviders = ServiceLoader.load(KeyProvider.class);
		for (KeyProvider keyProvider : keyProviders) {
			if (type.equals(keyProvider.accept())) {
				return keyProvider.resolve(RequestUtil.getHttpServletRequest());
			}
		}
		return EMPTY;
	}

}