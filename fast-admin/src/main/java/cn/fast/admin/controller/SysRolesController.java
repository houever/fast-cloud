package cn.fast.admin.controller;

import cn.fast.web.common.model.AuthRoles;
import cn.fast.web.utils.ObjectConvertUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.web.common.result.Result;
import cn.fast.admin.entity.SysRolesDept;
import cn.fast.admin.entity.SysRolesPermission;
import cn.fast.admin.model.dto.RoleDTO;
import cn.fast.admin.service.ISysPermissionService;
import cn.fast.admin.service.ISysRolesDeptService;
import cn.fast.admin.service.ISysRolesPermissionService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.fast.admin.entity.SysRoles;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import cn.fast.admin.service.ISysRolesService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import cn.fast.web.base.BaseController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Api(tags = {"角色表 前端控制器"})
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class SysRolesController extends BaseController<ISysRolesService, SysRoles, String> {

    private final ISysRolesService rolesService;
    private final ISysRolesPermissionService sysRolesPermissionService;
    private final ISysRolesDeptService sysRolesDeptService;

    @GetMapping(value = "/page")
    public Result getRolePage(Page<RoleDTO> page) {
        IPage<RoleDTO> p = rolesService.getPageList(page);
        return Result.success(p);
    }

    @PostMapping(value = "/add")
    public Result addRole(@RequestBody SysRoles sysRoles) {
        boolean save = rolesService.save(sysRoles);
        return Result.success(save);
    }

    @PostMapping(value = "/edit")
    public Result updateRole(@RequestBody SysRoles sysRoles) {
        boolean b = rolesService.updateById(sysRoles);
        return Result.success(b);
    }

    @DeleteMapping(value = "/del/{id}")
    public Result updateRole(@PathVariable(name = "id") String id) {
        boolean b = rolesService.removeById(id);
        return Result.success(b);
    }

    @PostMapping(value = "/editPerm")
    public Result editPerm(@RequestBody RoleDTO roleDTO) {
        Integer i = sysRolesPermissionService.deleteRolesPermByRid(roleDTO.getId());
        boolean b = false;
        SysRolesPermission sysRolesPermission = null;
        if (StrUtil.isNotEmpty(roleDTO.getPids())) {
            String[] pids = roleDTO.getPids().split(",");
            for (String pid : pids) {
                sysRolesPermission = new SysRolesPermission();
                sysRolesPermission.setRid(roleDTO.getId());
                sysRolesPermission.setPid(pid);
                b = sysRolesPermissionService.save(sysRolesPermission);
            }
        }
        return Result.success(b);
    }

    @PostMapping(value = "/editDept")
    public Result eidtRolesDept(@RequestBody RoleDTO roleDTO) {
        boolean b = SqlHelper.retBool(sysRolesDeptService.deleteRolesDeptByRid(roleDTO.getId()));
        List<SysRolesDept> list = new ArrayList<SysRolesDept>();
        SysRolesDept sysRolesDept = null;
        if (StrUtil.isNotEmpty(roleDTO.getDepts())) {
            String[] deptIds = roleDTO.getDepts().split(",");
            for (String deptId : deptIds) {
                sysRolesDept = new SysRolesDept();
                sysRolesDept.setRid(roleDTO.getId());
                sysRolesDept.setDeptId(deptId);
                list.add(sysRolesDept);
            }
            sysRolesDeptService.saveBatch(list,list.size());
        }
        return Result.success(b);
    }

    @PostMapping(value = "/getRolesByIds")
    public Result<List<AuthRoles>> getRolesByIds(String ids) {
        List<String> roleIds = Arrays.asList(ids.split(","));
        List<SysRoles> list = (List<SysRoles>) rolesService.listByIds(roleIds);
        List<AuthRoles> authRoles = ObjectConvertUtil.convertList(list, AuthRoles.class);
        return Result.success(authRoles);
    }
}
