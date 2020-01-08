package cn.fast.gateway.service;


import cn.fast.web.common.result.Result;

public interface IAuthService {

    boolean validToken(String token);
    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param authentication
     * @param url
     * @param method
     * @return Result
     */
    Result authenticate(String authentication, String url, String method);

    /**
     * 判断url是否在忽略的范围内
     * 只要是配置中的开头，即返回true
     *
     * @param url
     * @return
     */
    boolean ignoreAuthentication(String url);

    /**
     * 查看签权服务器返回结果，有权限返回true
     *
     * @param authResult
     * @return
     */
    boolean hasPermission(Result authResult);

    /**
     * 调用签权服务，判断用户是否有权限
     *
     * @param authentication
     * @param url
     * @param method
     * @return true/false
     */
    boolean hasPermission(String authentication, String url, String method);
}
