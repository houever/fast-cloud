package cn.fast.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.web.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.fast.admin.entity.SysRolesDept;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import cn.fast.admin.service.ISysRolesDeptService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import cn.fast.web.base.BaseController;

/**
 * <p>
 * 部门数据权限表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Api(tags = {"部门数据权限表 前端控制器"})
@RestController
@RequestMapping("/sys-roles-dept")
@AllArgsConstructor
public class SysRolesDeptController extends BaseController<ISysRolesDeptService, SysRolesDept, String> {

    private final ISysRolesDeptService SysRolesDeptService;

    /**
     * 根据id查询
     *
     * @return
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<SysRolesDept> getById(@PathVariable(name = "id") String id) {
        return Result.success(SysRolesDeptService.getById(id));
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
    public Result<IPage<SysRolesDept>> getByPage(@PathVariable(name = "current") Long current, @PathVariable(name = "size") Long size) {
        IPage<SysRolesDept> page = new Page<SysRolesDept>(current, size);
        return Result.success(SysRolesDeptService.page(page));
    }

    /**
     * 添加数据
     *
     * @param entity
     * @return Result
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "保存数据")
    public Result save(@RequestBody SysRolesDept entity) {
        return Result.success(SysRolesDeptService.save(entity));
    }

    /**
     * 修改数据
     *
     * @return
     */
    @PostMapping(value = "/modify")
    @ApiOperation(value = "更新数据")
    public Result modify(@RequestBody SysRolesDept entity) {
        return Result.success(SysRolesDeptService.updateById(entity));
    }

}
