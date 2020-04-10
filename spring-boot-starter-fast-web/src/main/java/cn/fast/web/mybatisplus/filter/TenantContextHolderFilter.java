package cn.fast.web.mybatisplus.filter;

import cn.fast.web.common.constant.SecurityConstants;
import cn.fast.web.common.model.OAuth2AccessToken;
import cn.fast.web.common.utils.GsonUtil;
import cn.fast.web.mybatisplus.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author houqijun
 * @date 2018/9/13
 */
@Slf4j
@Component
@Order(-100)
public class TenantContextHolderFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String header = request.getHeader(SecurityConstants.AUTH_HEADER);
        OAuth2AccessToken oAuth2AccessToken = GsonUtil.gsonToBean(header, OAuth2AccessToken.class);
        if (null != oAuth2AccessToken) {
            log.info("获取header中的租户ID为:{}",oAuth2AccessToken.getTenantId());
            TenantContextHolder.setTenantId(Integer.parseInt(oAuth2AccessToken.getTenantId()));
            TenantContextHolder.setUser(oAuth2AccessToken);
        } else {
            TenantContextHolder.setTenantId(1);
        }
        filterChain.doFilter(request, response);
        TenantContextHolder.clear();
    }

}
