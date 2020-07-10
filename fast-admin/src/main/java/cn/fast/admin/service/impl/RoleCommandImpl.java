package cn.fast.admin.service.impl;

import cn.fast.admin.command.RoleCommand;
import cn.fast.admin.entity.SysRoles;
import cn.fast.admin.repository.SysRolesRepository;
import cn.fast.web.base.BaseService;
import cn.fast.web.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author houqj
 * @date 2020-06-30 10:56
 */
@Slf4j
@Service
public class RoleCommandImpl implements RoleCommand {

    @Resource
    private BaseService<SysRoles> baseService;
    @Resource
    private SysRolesRepository sysRolesRepository;

    @Override
    public Result delById(String id) {

        SysRoles sysRoles = sysRolesRepository.findById(id).get();
        log.info("sysRoles==>{}",sysRoles);
        System.out.println(1/0);
        log.info("id==>{}",id);
        baseService.removeById(id);
        return Result.success();
    }
}
