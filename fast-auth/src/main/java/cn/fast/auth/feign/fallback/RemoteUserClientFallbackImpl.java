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
    public Result<AuthAccount> accountInfo(String username) {
        return Result.fail(ResultEnum.FEIGN_ERROR);
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
}
