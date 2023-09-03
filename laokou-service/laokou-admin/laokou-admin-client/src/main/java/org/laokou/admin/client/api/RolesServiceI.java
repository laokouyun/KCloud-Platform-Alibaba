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
package org.laokou.admin.client.api;

import org.laokou.admin.client.dto.common.clientobject.OptionCO;
import org.laokou.admin.client.dto.role.RoleGetQry;
import org.laokou.admin.client.dto.role.RoleInsertCmd;
import org.laokou.admin.client.dto.role.RoleListQry;
import org.laokou.admin.client.dto.role.RoleOptionListQry;
import org.laokou.admin.client.dto.role.clientobject.RoleCO;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;

import java.util.List;

/**
 * @author laokou
 */
public interface RolesServiceI {

    Result<Datas<RoleCO>> list(RoleListQry qry);

    Result<List<OptionCO>> optionList(RoleOptionListQry qry);

    Result<RoleCO> get(RoleGetQry qry);

    Result<Boolean> insert(RoleInsertCmd cmd);

}
