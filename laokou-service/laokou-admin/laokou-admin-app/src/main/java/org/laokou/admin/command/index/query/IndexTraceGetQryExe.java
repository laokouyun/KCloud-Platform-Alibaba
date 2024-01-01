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

package org.laokou.admin.command.index.query;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.dto.index.IndexTraceGetQry;
import org.laokou.common.elasticsearch.template.ElasticsearchTemplate;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.i18n.dto.Search;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

import static org.laokou.common.i18n.common.Constant.TRACE_INDEX;
import static org.laokou.common.mybatisplus.database.dataobject.BaseDO.ID;

/**
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class IndexTraceGetQryExe {

	private final ElasticsearchTemplate elasticsearchTemplate;

	public Result<Map<String, Object>> execute(IndexTraceGetQry qry) {
		Search search = new Search();
		search.setIndexNames(new String[] { TRACE_INDEX });
		search.setPageSize(1);
		search.setPageNum(1);
		search.setOrQueryList(Collections.singletonList(new Search.Query(ID, qry.getId())));
		return Result.of(elasticsearchTemplate.highlightSearchIndex(search).getRecords().getFirst());
	}

}