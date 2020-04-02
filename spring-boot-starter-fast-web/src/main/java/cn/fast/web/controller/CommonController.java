package cn.fast.web.controller;
import cn.fast.web.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author houqj
 * @date 2019-12-12 16:21
 */
@Api(tags={"公共请求访问"})
@RestController
public class CommonController {

    @GetMapping(value = "/")
    public Result all(){
        return Result.success();
    }

    @GetMapping(value = "/csrf")
    public Result csrf(){
        return Result.success();
    }
}
