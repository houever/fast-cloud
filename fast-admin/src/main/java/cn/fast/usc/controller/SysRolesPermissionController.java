package cn.fast.usc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.web.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.fast.usc.entity.SysRolesPermission;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import cn.fast.usc.service.ISysRolesPermissionService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import cn.fast.web.base.BaseController;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Api(tags = {"角色权限表 前端控制器"})
@RestController
@RequestMapping("/sys-roles-permission")
@AllArgsConstructor
public class SysRolesPermissionController extends BaseController<ISysRolesPermissionService, SysRolesPermission, String> {

    private final ISysRolesPermissionService SysRolesPermissionService;

    /**
     * 根据id查询
     *
     * @return
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<SysRolesPermission> getById(@PathVariable(name = "id") String id) {
        return Result.success(SysRolesPermissionService.getById(id));
    }

    /**
     * 分页列表
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping(value = "/page/{current}/{size}")
    @ApiOperation(value = "分页获取")
    public Result<IPage<SysRolesPermission>> getByPage(@PathVariable(name = "current") Long current, @PathVariable(name = "size") Long size) {
        IPage<SysRolesPermission> page = new Page<SysRolesPermission>(current, size);
        return Result.success(SysRolesPermissionService.page(page));
    }

    /**
     * 添加数据
     *
     * @param entity
     * @return Result
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "保存数据")
    public Result save(@RequestBody SysRolesPermission entity) {
        return Result.success(SysRolesPermissionService.save(entity));
    }

    /**
     * 修改数据
     *
     * @return
     */
    @PostMapping(value = "/modify")
    @ApiOperation(value = "更新数据")
    public Result modify(@RequestBody SysRolesPermission entity) {
        return Result.success(SysRolesPermissionService.updateById(entity));
    }

}
