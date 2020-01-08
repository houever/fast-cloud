package cn.fast.admin.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.fast.web.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.fast.admin.entity.SysDict;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import cn.fast.admin.service.ISysDictService;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import cn.fast.web.base.BaseController;

/**
 * <p>
 * 系统字典分类表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Api(tags={"系统字典分类表 前端控制器"})
@RestController
@RequestMapping("/dict")
@AllArgsConstructor
public class SysDictController extends BaseController<ISysDictService,SysDict,String> {

private final ISysDictService SysDictService;

/**
  * 根据id查询
  * @return
  */
@GetMapping(value = "/get/{id}")
@ApiOperation(value = "通过id获取")
public Result<SysDict> getById(@PathVariable(name = "id") String id){
        return Result.success(SysDictService.getById(id));
    }

/**
  * 分页列表
  * @return
  * @param current
  * @param size
  */
@GetMapping(value = "/page/{current}/{size}")
@ApiOperation(value = "分页获取")
public Result<IPage<SysDict>>getByPage(@PathVariable(name = "current")Long current,@PathVariable(name = "size")Long size){
        IPage<SysDict> page=new Page<SysDict>(current,size);
        return Result.success(SysDictService.page(page));
    }
/**
  * 添加数据
  * @param entity
  * @return Result
  */
@PostMapping(value = "/add")
@ApiOperation(value = "保存数据")
public Result save(@RequestBody SysDict entity){
        return Result.success(SysDictService.save(entity));
    }
/**
  * 修改数据
  * @return
  */
@PostMapping(value = "/modify")
@ApiOperation(value = "更新数据")
public Result modify(@RequestBody SysDict entity){
        return Result.success(SysDictService.updateById(entity));
    }

}
