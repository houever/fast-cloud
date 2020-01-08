package cn.fast.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.web.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.fast.admin.entity.SysLoginConfig;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import cn.fast.admin.service.ISysLoginConfigService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import cn.fast.web.base.BaseController;

/**
 * <p>
 * 登录背景配置表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Api(tags = {"登录背景配置表 前端控制器"})
@RestController
@RequestMapping("/sysLoginConfig")
@AllArgsConstructor
public class SysLoginConfigController extends BaseController<ISysLoginConfigService, SysLoginConfig, String> {

    private final ISysLoginConfigService SysLoginConfigService;

    /**
     * 添加数据
     *
     * @param entity
     * @return Result
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "保存数据")
    public Result save(@RequestBody SysLoginConfig entity) {
        return Result.success(SysLoginConfigService.save(entity));
    }

    @ApiOperation(value = "查询背景图片")
    @GetMapping(value = "/bg")
    public Result getBg(){
        QueryWrapper<SysLoginConfig> wrapper = new QueryWrapper<>();
        wrapper.eq("is_bg",1);
        return Result.success(SysLoginConfigService.getOne(wrapper));
    }

    @ApiOperation(value = "设置为背景图片")
    @PostMapping(value = "/set/{id}")
    public Result setBg(@PathVariable("id") String id){
        boolean b = SysLoginConfigService.update(Wrappers.<SysLoginConfig>lambdaUpdate().set(SysLoginConfig::getIsBg, 0));
        b = SysLoginConfigService.update(Wrappers.<SysLoginConfig>lambdaUpdate().set(SysLoginConfig::getIsBg, 1).eq(SysLoginConfig::getId, id));
        return Result.success(b);
    }

}
