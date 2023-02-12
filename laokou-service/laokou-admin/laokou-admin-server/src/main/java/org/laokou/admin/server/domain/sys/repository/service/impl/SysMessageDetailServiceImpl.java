/**
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *   http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.laokou.admin.server.domain.sys.repository.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.laokou.admin.server.domain.sys.entity.SysMessageDetailDO;
import org.laokou.admin.server.domain.sys.repository.mapper.SysMessageDetailMapper;
import org.laokou.admin.server.domain.sys.repository.service.SysMessageDetailService;
import org.laokou.common.mybatisplus.utils.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author laokou
 */
@Service
@RequiredArgsConstructor
public class SysMessageDetailServiceImpl extends ServiceImpl<SysMessageDetailMapper, SysMessageDetailDO> implements SysMessageDetailService {

    private final MapperUtil<SysMessageDetailDO> mapperUtil;

    @Override
    public Integer getVersion(Long id) {
        return this.baseMapper.getVersion(id);
    }

    @Override
    public void insertBatch(List<SysMessageDetailDO> list) {
        mapperUtil.insertBatch(list,500,this.baseMapper);
    }
}
