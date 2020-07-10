package cn.fast.admin.controller;

import cn.fast.admin.command.UserCommand;
import cn.fast.web.common.result.Result;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author houqj
 * @date 2020-06-30 10:28
 */
@RestController
public class UserController implements UserCommand {

    @Resource
    private UserCommand userCommand;

    @Override
    public Result login() {
        return userCommand.login();
    }
}
