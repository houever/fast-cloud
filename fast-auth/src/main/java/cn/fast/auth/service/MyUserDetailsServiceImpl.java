package cn.fast.auth.service;

import cn.fast.auth.feign.RemoteUserClient;
import cn.fast.web.common.constant.SecurityConstants;
import cn.fast.web.common.model.AuthAccount;
import cn.fast.web.common.result.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户详细信息
 *
 * @author Houqijun
 */
@Slf4j
@Service
@AllArgsConstructor
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    private final RemoteUserClient remoteUserClient;
    private final CacheManager cacheManager;

    /**
     * 用户密码登录
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("username====>{}",username);
//        Cache cache = cacheManager.getCache("user_details");
//        if (cache != null && cache.get(username) != null) {
//            return (SysUser) cache.get(username).get();
//        }
        Result<AuthAccount> result = remoteUserClient.info(username);
        log.debug("result====>{}",result);
        UserDetails userDetails = getUserDetails(result);
        //cache.put(username, userDetails);
        return userDetails;
    }


    /**
     * 根据社交登录code 登录
     *
     * @param inStr TYPE@CODE
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserBySocial(String inStr) throws UsernameNotFoundException {
        return getUserDetails(remoteUserClient.social(inStr, SecurityConstants.FROM_IN));
    }

    /**
     * 构建userdetails
     *
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(Result<AuthAccount> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        AuthAccount account = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if(account.getRoles().size()>0){
            // 获取角色
            account.getRoles().forEach(role -> dbAuthsSet.add(role));
        }

        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));

        // 构造security用户
        return new SysUser(account.getId(),
                account.getDeptId(),
                account.getTenantId(),
                account.getUsername(),
                account.getPassword(),
                account.isEnabled(),
                account.isAccountNonExpired(),
                account.isCredentialsNonExpired(),
                account.isAccountNonLocked(),
                authorities,account.getBtns());
    }
}
