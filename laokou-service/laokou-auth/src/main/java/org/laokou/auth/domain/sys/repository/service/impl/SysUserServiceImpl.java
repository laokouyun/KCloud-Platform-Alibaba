/**
 * Copyright (c) 2022 KCloud-Platform Authors. All Rights Reserved.
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
 */
package org.laokou.auth.domain.sys.repository.service.impl;
import org.laokou.auth.domain.sys.repository.mapper.SysUserMapper;
import org.laokou.auth.domain.sys.repository.service.SysUserService;
import org.laokou.common.user.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author Kou Shenhai
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetail getUserDetail(Long id, String username) {
        return sysUserMapper.getUserDetail(id, username);
    }

    @Override
    public String getUsernameByOpenid(String zfbOpenid) {
        return sysUserMapper.getUsernameByOpenid(zfbOpenid);
    }

    @Override
    public void updateZfbOpenid(Long id, String zfbOpenid) {
        sysUserMapper.updateZfbOpenid(id,zfbOpenid);
    }


}