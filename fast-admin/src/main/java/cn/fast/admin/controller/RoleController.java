package cn.fast.admin.controller;

import cn.fast.admin.command.RoleCommand;
import cn.fast.web.common.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author houqj
 * @date 2020-06-30 10:54
 */
@RestController
@RequestMapping(value = "/roles")
public class RoleController implements RoleCommand {

    @Resource
    RoleCommand roleCommand;

    @Override
    public Result delById(String id) {
        return roleCommand.delById(id);
    }
}
