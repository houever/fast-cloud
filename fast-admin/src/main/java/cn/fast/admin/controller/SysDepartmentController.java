package cn.fast.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.web.common.result.Result;
import cn.fast.admin.entity.SysAccount;
import cn.fast.admin.model.dto.DeptDTO;
import cn.fast.admin.service.ISysAccountService;
import cn.fast.admin.utils.DeptTreeUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.fast.admin.entity.SysDepartment;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import cn.fast.admin.service.ISysDepartmentService;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import cn.fast.web.base.BaseController;

/**
 * <p>
 * 系统部门表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Slf4j
@Api(tags = {"系统部门表 前端控制器"})
@RestController
@RequestMapping("/dept")
@AllArgsConstructor
public class SysDepartmentController extends BaseController<ISysDepartmentService, SysDepartment, String> {

    private final ISysDepartmentService sysDepartmentService;
    private final ISysAccountService sysAccountService;

    @PostMapping(value = "/search")
    public Result searchDept(@RequestBody SysDepartment department){
        List<SysDepartment> list = sysDepartmentService.list(new QueryWrapper<SysDepartment>().like("dept_name", department.getDeptName()));
        return Result.success(list);
    }

    @GetMapping(value = "/tree")
    public Result getDeptTree(){
        List<SysDepartment> list = sysDepartmentService.list();
        list.forEach((d)->{log.debug("{}",d);});
        List<DeptDTO> deptDTOS = new DeptTreeUtil().deptTree(list);
        return Result.success(deptDTOS);
    }
    @PostMapping(value = "/add")
    public Result addDept(@RequestBody SysDepartment sysDepartment){
        return Result.success(sysDepartmentService.save(sysDepartment));
    }

    @PostMapping(value = "/edit")
    public Result editDept(@RequestBody SysDepartment sysDepartment){
        return Result.success(sysDepartmentService.updateById(sysDepartment));
    }
    @GetMapping(value = "/node/{id}")
    public Result getDeptNode(@PathVariable(name = "id") String id){

        return Result.success(sysDepartmentService.list(new QueryWrapper<SysDepartment>().eq("parent_id",id)));
    }

    @GetMapping(value = "/users/{id}")
    public Result getUsers(@PathVariable(name = "id") String id){
        return Result.success(sysAccountService.list(new QueryWrapper<SysAccount>().eq("dept_id",id)));
    }
}
