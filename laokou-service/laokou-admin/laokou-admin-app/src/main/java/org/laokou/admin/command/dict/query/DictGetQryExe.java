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

package org.laokou.admin.command.dict.query;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.client.dto.dict.DictGetQry;
import org.laokou.admin.client.dto.dict.clientobject.DictCO;
import org.laokou.admin.domain.dict.Dict;
import org.laokou.admin.domain.gateway.DictGateway;
import org.laokou.common.core.utils.ConvertUtil;
import org.laokou.common.i18n.dto.Result;
import org.springframework.stereotype.Component;

/**
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class DictGetQryExe {

	private final DictGateway dictGateway;

	public Result<DictCO> execute(DictGetQry qry) {
		Dict dict = dictGateway.getById(qry.getId());
		return Result.of(ConvertUtil.sourceToTarget(dict, DictCO.class));
	}

}