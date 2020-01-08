package cn.fast.auth.feign.fallback;

import cn.fast.auth.feign.RemoteUserClient;
import cn.fast.web.common.model.AuthAccount;
import cn.fast.web.common.model.AuthRoles;
import cn.fast.web.common.result.Result;
import cn.fast.web.common.result.ResultEnum;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: mycloud-admin
 * @description:
 * @author: houqijun
 * @create: 2019-03-03 10:34
 **/
@Slf4j
@Component
public class RemoteUserClientFallbackImpl implements RemoteUserClient {

    @Setter
    private Throwable cause;

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return Result
     */
    @Override
    public Result<AuthAccount> info(String username) {
        return Result.fail(ResultEnum.FEIGN_ERROR);
    }

    @Override
    public Result<List<String>> getAuthRolesByRequestUrl(String url) {
        return null;
    }

    @Override
    public Result queryAllMenusByName(String username) {
        return null;
    }

    @Override
    public Result<List<AuthAccount>> getAccountByIds(String ids) {
        return null;
    }

    @Override
    public Result<List<AuthRoles>> getRolesByIds(String ids) {
        return null;
    }

    @Override
    public Result<List<AuthAccount>> findUserByRoleId(String id) {
        return null;
    }

    /**
     * 通过社交账号或手机号查询用户、角色信息
     *
     * @param inStr appid@code
     * @param from  调用标志
     * @return
     */
    @Override
    public Result<AuthAccount> social(String inStr, String from) {
        return Result.fail(ResultEnum.FEIGN_ERROR);
    }

    /**
     * 根据userid查询user
     *
     * @param userId
     * @return
     */
    @Override
    public Result<AuthAccount> getAccountById(String userId) {
        return Result.fail(ResultEnum.FEIGN_ERROR);
    }
}
