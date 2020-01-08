package cn.fast.web.config;
import cn.fast.web.common.annotations.CurrentUser;
import cn.fast.web.common.constant.SecurityConstants;
import cn.fast.web.common.model.OAuth2AccessToken;
import cn.fast.web.common.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *
 */
@Slf4j
public class CurrentUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 根据当前header里面的用户信息注入用户对象
//        CurrentUser currentUserAnnotation = methodParameter.getParameterAnnotation(CurrentUser.class);
//        //从Session 获取用户
//        Object object = webRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_SESSION);
////从  accessToken获得用户信息
//        if (object == null) {
//            String token = webRequest.getHeader("Authorization");
//            if (token == null) {
//                token = webRequest.getParameter("accessToken");
//            }
//            //为了测试先写死用户名
//            //TODO: 取真实用户
//            return new UserBean(1L,"admin");
//        }
//        return object;
        CurrentUser currentUserAnnotation = methodParameter.getParameterAnnotation(CurrentUser.class);
        OAuth2AccessToken oAuth2AccessToken = GsonUtil.gsonToBean(nativeWebRequest.getHeader(SecurityConstants.AUTH_HEADER), OAuth2AccessToken.class);
        log.info("methodParameter:{}",methodParameter);
        log.info("NativeWebRequest:{}",nativeWebRequest);
        log.info("oAuth2AccessToken:{}",oAuth2AccessToken);
        return null;
    }
}
