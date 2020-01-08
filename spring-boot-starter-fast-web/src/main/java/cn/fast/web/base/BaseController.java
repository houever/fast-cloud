package cn.fast.web.base;

import cn.fast.web.common.controller.ResultController;
import cn.fast.web.common.result.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Administrator
 * @Package cocom.mycloud.admin.base
 * @Description: BaseController
 * @date 2018/4/17 11:08
 */
@Slf4j
public abstract class BaseController<K extends BaseService<T>, T, ID extends Serializable> extends ResultController {
    /**
     * 获取service
     *
     * @return
     */
    @Autowired
    private K baseService;

    @ApiOperation(value = "主键查询", notes = "主键查询", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public Result<T> get(@PathVariable ID id) {
        return Result.success(baseService.getById(id));
    }

    @ApiOperation(value = "查询所有信息", notes = "查询所有信息", httpMethod = "GET")
    @GetMapping(value = "/all")
    public Result<Collection<T>> getAll(T t) {
        return Result.success(baseService.getAll(t));
    }

    @ApiOperation(value = "查询所有信息", notes = "查询所有信息", httpMethod = "GET")
    @GetMapping(value = "/findAll")
    public Result<Collection<T>> findAll() {
        return Result.success(baseService.findAll());
    }

    @ApiOperation(value = "分页条件查询", notes = "分页查询", httpMethod = "GET")
    @PostMapping(value = "/page/{current}/{size}")
    public Result<IPage<T>> getByPage(@PathVariable(name = "current") Long current, @PathVariable(name = "size") Long size, @RequestBody QueryWrapper<T> wrapper) {
        IPage<T> page = new Page<T>(current, size);
        return Result.success(baseService.page(page, wrapper));
    }

    @ApiOperation(value = "修改", httpMethod = "POST")
    @PostMapping(value = "/update")
    public Result update(@RequestBody @Validated T entity) {
        boolean b = baseService.updateById(entity);
        return Result.success(b);
    }

    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @DeleteMapping(value = "/del/{ids}")
    public Result<Object> delAllByIds(@PathVariable ID[] ids) {
        boolean b = false;
        for (ID id : ids) {
            b = baseService.removeById(id);
        }
        return Result.success("批量删除成功");
    }
}
