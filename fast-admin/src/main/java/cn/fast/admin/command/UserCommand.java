package cn.fast.admin.command;

import cn.fast.web.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author houqj
 * @date 2020-06-30 10:30
 */
public interface UserCommand {

    @GetMapping("/login")
    public Result login();
}
