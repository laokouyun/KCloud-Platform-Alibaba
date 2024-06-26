/*
 * Copyright (c) 2022-2024 KCloud-Platform-Alibaba Author or Authors. All Rights Reserved.
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

package org.laokou.flowable.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.laokou.common.i18n.dto.Datas;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.idempotent.annotation.Idempotent;
import org.laokou.common.log.annotation.OperateLog;
import org.laokou.common.trace.annotation.TraceLog;
import org.laokou.flowable.api.DefinitionsServiceI;
import org.laokou.flowable.dto.definition.*;
import org.laokou.flowable.dto.definition.clientobject.DefinitionCO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * @author laokou
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "DefinitionsController", description = "流程定义")
@RequestMapping("v1/definitions")
public class FlowDefinitionsController {

	private final DefinitionsServiceI definitionsServiceI;

	@Idempotent
	@PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "流程定义", description = "新增流程")
	@PreAuthorize("hasAuthority('definitions:create')")
	@OperateLog(module = "流程定义", operation = "新增流程")
	public void create(@RequestPart("file") MultipartFile file) {
		definitionsServiceI.create(new DefinitionCreateCmd(file));
	}

	@TraceLog
	@PostMapping("list")
	@Operation(summary = "流程定义", description = "查询流程列表")
	@PreAuthorize("hasAuthority('definitions:list')")
	public Result<Datas<DefinitionCO>> findList(@RequestBody DefinitionListQry qry) {
		return definitionsServiceI.findList(qry);
	}

	@TraceLog
	@GetMapping("{definitionId}/diagram")
	@Operation(summary = "流程定义", description = "查看流程图")
	@PreAuthorize("hasAuthority('definitions:diagram')")
	public Result<String> findDiagram(@PathVariable("definitionId") String definitionId) {
		return definitionsServiceI.findDiagram(new DefinitionDiagramGetQry(definitionId));
	}

	@DeleteMapping("{deploymentId}")
	@Operation(summary = "流程定义", description = "删除流程")
	@OperateLog(module = "流程定义", operation = "删除流程")
	@PreAuthorize("hasAuthority('definitions:remove')")
	public void remove(@PathVariable("deploymentId") String deploymentId) {
		definitionsServiceI.remove(new DefinitionRemoveCmd(deploymentId));
	}

	@PutMapping("{definitionId}/suspend")
	@Operation(summary = "流程定义", description = "挂起流程")
	@OperateLog(module = "流程定义", operation = "挂起流程")
	@PreAuthorize("hasAuthority('definitions:suspend')")
	public void suspend(@PathVariable("definitionId") String definitionId) {
		definitionsServiceI.suspend(new DefinitionSuspendCmd(definitionId));
	}

	@PutMapping("{definitionId}/activate")
	@Operation(summary = "流程定义", description = "激活流程")
	@OperateLog(module = "流程定义", operation = "激活流程")
	@PreAuthorize("hasAuthority('definitions:activate')")
	public void activate(@PathVariable("definitionId") String definitionId) {
		definitionsServiceI.activate(new DefinitionActivateCmd(definitionId));
	}

}
