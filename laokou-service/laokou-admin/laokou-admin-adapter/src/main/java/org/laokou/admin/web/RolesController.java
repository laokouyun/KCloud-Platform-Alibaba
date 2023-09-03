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
package org.laokou.admin.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.client.api.RolesServiceI;
import org.laokou.admin.client.dto.role.RoleGetQry;
import org.laokou.admin.client.dto.role.RoleListQry;
import org.laokou.admin.client.dto.role.RoleOptionListQry;
import org.laokou.admin.client.dto.role.clientobject.RoleCO;
import org.laokou.admin.domain.annotation.OperateLog;
import org.laokou.common.data.cache.annotation.DataCache;
import org.laokou.common.data.cache.enums.Cache;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.trace.annotation.TraceLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author laokou
 */
@RestController
@Tag(name = "RolesController", description = "角色")
@RequiredArgsConstructor
public class RolesController {

	private final RolesServiceI rolesServiceI;

	@TraceLog
	@PostMapping("v1/roles/list")
	@Operation(summary = "查询", description = "查询")
	@PreAuthorize("hasAuthority('roles:list')")
	public Result<Datas<RoleCO>> list(@RequestBody RoleListQry qry) {
		return rolesServiceI.list(qry);
	}

	@TraceLog
	@PostMapping("v1/roles/option-list")
	@Operation(summary = "下拉列表", description = "下拉列表")
	public Result<?> optionList() {
		return rolesServiceI.optionList(new RoleOptionListQry());
	}

	@TraceLog
	@GetMapping("v1/roles/{id}")
	@Operation(summary = "查看", description = "查看")
	@DataCache(name = "roles", key = "#id")
	public Result<?> get(@PathVariable("id") Long id) {
		return rolesServiceI.get(new RoleGetQry(id));
	}

	@TraceLog
	@PostMapping("v1/roles")
	@Operation(summary = "新增", description = "新增")
	@OperateLog(module = "角色管理", operation = "新增")
	@PreAuthorize("hasAuthority('roles:insert')")
	public Result<Boolean> insert() {
		return Result.of(null);
	}

	@TraceLog
	@PutMapping("v1/roles")
	@Operation(summary = "修改", description = "修改")
	@OperateLog(module = "角色管理", operation = "修改")
	@PreAuthorize("hasAuthority('roles:update')")
	@DataCache(name = "roles", key = "#dto.id", type = Cache.DEL)
	public Result<Boolean> update() {
		return Result.of(null);
	}

	@TraceLog
	@DeleteMapping("v1/roles/{id}")
	@Operation(summary = "删除", description = "删除")
	@OperateLog(module = "角色管理", operation = "删除")
	@PreAuthorize("hasAuthority('roles:delete')")
	@DataCache(name = "roles", key = "#id", type = Cache.DEL)
	public Result<Boolean> delete(@PathVariable("id") Long id) {
		return Result.of(null);
	}

}
