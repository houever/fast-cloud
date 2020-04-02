package cn.fast.admin.controller;

import cn.fast.admin.entity.SysLog;
import cn.fast.admin.service.ISysLogService;
import cn.fast.web.base.BaseController;
import cn.fast.web.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统日志表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Slf4j
@Api(tags = {"系统日志表 前端控制器"})
@RestController
@RequestMapping("/sys-log")
@AllArgsConstructor
public class SysLogController extends BaseController<ISysLogService, SysLog, String> {

    private final ISysLogService SysLogService;

    /**
     * 添加数据
     * @param entity
     * @return Result
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "保存数据")
    public Result save(@RequestBody @Validated SysLog entity) {
        log.debug("保存日志，参数:{}",entity);
        return Result.success(SysLogService.save(entity));
    }
}
