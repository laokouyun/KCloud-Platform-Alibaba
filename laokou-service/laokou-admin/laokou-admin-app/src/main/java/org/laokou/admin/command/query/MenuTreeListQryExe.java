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
package org.laokou.admin.command.query;

import lombok.RequiredArgsConstructor;
import org.laokou.admin.client.dto.clientobject.MenuCO;
import org.laokou.admin.domain.gateway.MenuGateway;
import org.laokou.admin.domain.menu.Menu;
import org.laokou.common.core.utils.ConvertUtil;
import org.laokou.common.core.utils.TreeUtil;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.security.utils.UserUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author laokou
 */
@Component
@RequiredArgsConstructor
public class MenuTreeListQryExe {

    private final MenuGateway menuGateway;

    public Result<MenuCO> execute() {
        List<Menu> menuList = menuGateway.list(0, UserUtil.user());
        List<MenuCO> menus = ConvertUtil.sourceToTarget(menuList, MenuCO.class);
        return Result.of(TreeUtil.buildTreeNode(menus,MenuCO.class));
    }

}
