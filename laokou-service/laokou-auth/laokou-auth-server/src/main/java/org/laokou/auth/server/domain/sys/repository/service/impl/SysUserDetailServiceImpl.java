/**
 * Copyright (c) 2022 KCloud-Platform-Alibaba Authors. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.laokou.auth.server.domain.sys.repository.service.impl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.laokou.auth.client.user.UserDetail;
import org.laokou.auth.server.domain.sys.repository.service.SysDeptService;
import org.laokou.auth.server.domain.sys.repository.service.SysMenuService;
import org.laokou.common.core.utils.HttpContextUtil;
import org.laokou.common.core.utils.MessageUtil;
import org.laokou.common.swagger.exception.ErrorCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import java.util.List;
/**
 * @author laokou
 */
@RequiredArgsConstructor
public class SysUserDetailServiceImpl implements UserDetailsService {

    private final SysUserServiceImpl sysUserService;
    private final SysMenuService sysMenuService;
    private final SysDeptService sysDeptService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        // 多租户查询
        UserDetail userDetail = sysUserService.getUserDetail(loginName,0L);
        if (userDetail == null) {
            throw new UsernameNotFoundException(MessageUtil.getMessage(ErrorCode.ACCOUNT_PASSWORD_ERROR));
        }
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String password = request.getParameter(OAuth2ParameterNames.PASSWORD);
        String clientPassword = userDetail.getPassword();
        if (!passwordEncoder.matches(password, clientPassword)) {
            throw new UsernameNotFoundException(MessageUtil.getMessage(ErrorCode.ACCOUNT_PASSWORD_ERROR));
        }
        // 是否锁定
        if (!userDetail.isEnabled()) {
            throw new UsernameNotFoundException(MessageUtil.getMessage(ErrorCode.ACCOUNT_DISABLE));
        }
        Long userId = userDetail.getUserId();
        Integer superAdmin = userDetail.getSuperAdmin();
        // 权限标识列表
        List<String> permissionsList = sysMenuService.getPermissionsList(superAdmin,userId);
        if (CollectionUtils.isEmpty(permissionsList)) {
            throw new UsernameNotFoundException(MessageUtil.getMessage(ErrorCode.NOT_PERMISSIONS));
        }
        List<Long> deptIds = sysDeptService.getDeptIds(superAdmin, userId);
        userDetail.setDeptIds(deptIds);
        userDetail.setPermissionList(permissionsList);
        return userDetail;
    }
}
