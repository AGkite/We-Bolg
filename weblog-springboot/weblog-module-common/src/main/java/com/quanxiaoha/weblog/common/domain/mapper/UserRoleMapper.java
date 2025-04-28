package com.quanxiaoha.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.quanxiaoha.weblog.common.domain.dos.UserRoleDO;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    default List<UserRoleDO> selectByUsername(String username) {
        return selectList(Wrappers.<UserRoleDO>lambdaQuery()
                .eq(UserRoleDO::getUsername, username));
    }

}
