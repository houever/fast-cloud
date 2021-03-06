package cn.fast.admin.controller;

import cn.fast.web.common.utils.SnowFlakeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.web.common.result.Result;
import cn.fast.admin.model.dto.MenuTreeDTO;
import cn.fast.admin.utils.MenuTreeUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.fast.admin.entity.SysPermission;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import cn.fast.admin.service.ISysPermissionService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import cn.fast.web.base.BaseController;

/**
 * <p>
 * 权限菜单表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Api(tags = {"权限菜单表 前端控制器"})
@RestController
@RequestMapping("/permission")
@AllArgsConstructor
public class SysPermissionController extends BaseController<ISysPermissionService, SysPermission, String> {

    private final ISysPermissionService sysPermissionService;

    @ApiOperation(value = "查询菜单列表")
    @GetMapping(value = "/menus")
    public Result allPermissionsTree() {
        List<MenuTreeDTO> menuTreeDTOS = new MenuTreeUtil().MenusTree(sysPermissionService.list());
        return Result.success(menuTreeDTOS);
    }

    @ApiOperation(value = "查询菜单树")
    @GetMapping(value = "/tree")
    public Result permissionsTree() {
        List<MenuTreeDTO> menuTreeDTOS = new MenuTreeUtil().MenusTree(sysPermissionService.list(Wrappers.<SysPermission>query().orderByAsc("sort")));
        return Result.success(menuTreeDTOS);
    }

    @PostMapping(value = "/add")
    public Result addPermission(@RequestBody SysPermission sysPermission) {
        return Result.success(sysPermissionService.save(sysPermission));
    }

    @PostMapping(value = "/edit")
    public Result editPermission(@RequestBody SysPermission sysPermission) {
        if (sysPermission.getId().equals("0")) {
            sysPermission.setId(String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId()));
            return Result.success(sysPermissionService.save(sysPermission));
        } else {
            return Result.success(sysPermissionService.updateById(sysPermission));
        }
    }

    @DeleteMapping(value = "/delById/{id}")
    public Result delById(@PathVariable(name = "id") String id) {
        return Result.success(sysPermissionService.removeById(id));
    }

}
