package cn.fast.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.web.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.fast.admin.entity.SysDictData;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import cn.fast.admin.service.ISysDictDataService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import cn.fast.web.base.BaseController;

/**
 * <p>
 * 系统分类数据表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Api(tags = {"系统分类数据表 前端控制器"})
@RestController
@RequestMapping("/dictdata")
@AllArgsConstructor
public class SysDictDataController extends BaseController<ISysDictDataService, SysDictData, String> {

    private final ISysDictDataService SysDictDataService;

    /**
     * 根据id查询
     *
     * @return
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "通过id获取")
    public Result<SysDictData> getById(@PathVariable(name = "id") String id) {
        return Result.success(SysDictDataService.getById(id));
    }

    /**
     * 分页列表
     *
     * @return
     */
    @GetMapping(value = "/page")
    @ApiOperation(value = "分页获取")
    public Result<IPage<SysDictData>> getByPage(Page<SysDictData> page, SysDictData sysDictData) {
        QueryWrapper<SysDictData> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(sysDictData.getTitle())) {
            wrapper.like("title", sysDictData.getTitle());
        }
        if (null != sysDictData.getStatus()) {
            wrapper.eq("status", sysDictData.getStatus());
        }
        wrapper.orderByAsc("sort");
        wrapper.eq("dict_id", sysDictData.getDictId());
        return Result.success(SysDictDataService.page(page, wrapper));
    }

    /**
     * 添加数据
     *
     * @param entity
     * @return Result
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "保存数据")
    public Result save(@RequestBody SysDictData entity) {
        return Result.success(SysDictDataService.save(entity));
    }


    @GetMapping(value = "/byType/{type}")
    public Result getDictDataByType(@PathVariable(name = "type") String type) {
        return Result.success(SysDictDataService.list(new QueryWrapper<SysDictData>().eq("type", type)));
    }

}
