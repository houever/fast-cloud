package cn.fast.web.common.controller;


import cn.fast.web.common.result.Result;
import cn.fast.web.common.result.ResultEnum;

/**
 * @author houqj
 * @date 2020-01-02 16:09
 */
public abstract class ResultController {

    protected Result SUCCESS(){
        return Result.success();
    }

    protected Result SUCCESS(Object o){
        return Result.success(o);
    }

    protected Result FAIL(String message){
        return Result.fail(message);
    }

    protected Result FAIL(ResultEnum resultEnum){
        return Result.fail(resultEnum);
    }
}
