package cn.fast.auth.feign;

import cn.fast.auth.feign.factory.RemoteUserClientFallbackFactory;
import cn.fast.common.ServiceConstants;
import cn.fast.web.common.constant.SecurityConstants;
import cn.fast.web.common.model.AuthAccount;
import cn.fast.web.common.model.AuthRoles;
import cn.fast.web.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户中心对外开放接口
 */
@FeignClient(name = ServiceConstants.USC_SERVICE,fallbackFactory = RemoteUserClientFallbackFactory.class)
public interface RemoteUserClient {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return Result
     */
    @PostMapping(value = "/account/login",produces = "application/json;charset=UTF-8")
    Result<AuthAccount> info(@RequestParam("username") String username);

    @PostMapping(value = "/account/roles",produces = "application/json;charset=UTF-8")
    Result<List<String>> getAuthRolesByRequestUrl(@RequestParam("url") String url);

    @PostMapping(value = "/account/info",produces = "application/json;charset=UTF-8")
    Result queryAllMenusByName(@RequestParam("username") String username);

    @PostMapping(value = "/account/getAccountByIds",produces = "application/json;charset=UTF-8")
    Result<List<AuthAccount>> getAccountByIds(@RequestParam("ids") String ids);

    @PostMapping(value = "/role/getRolesByIds",produces = "application/json;charset=UTF-8")
    Result<List<AuthRoles>> getRolesByIds(@RequestParam("ids") String ids);

    @PostMapping(value = "/account/findUserByRoleId",produces = "application/json;charset=UTF-8")
    Result<List<AuthAccount>> findUserByRoleId(@RequestParam("id") String id);

    /**
     * 通过社交账号或手机号查询用户、角色信息
     *
     * @param inStr appid@code
     * @param from  调用标志
     * @return
     */
    @GetMapping("/social/info/{inStr}")
    Result<AuthAccount> social(@PathVariable("inStr") String inStr, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 根据userid查询user
     * @param id
     * @return
     */
    @GetMapping("/account/getById/{id}")
    Result<AuthAccount> getAccountById(@PathVariable("id") String id);
}
