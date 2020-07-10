package cn.fast.admin.command;

import cn.fast.web.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author houqj
 * @date 2020-06-30 10:54
 */
public interface RoleCommand{

    @GetMapping(value = "/delById/{id}")
    public Result delById(@PathVariable(value = "id")String id);
}