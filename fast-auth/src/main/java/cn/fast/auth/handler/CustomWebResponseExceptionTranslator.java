package cn.fast.auth.handler;

import cn.fast.web.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;
@Slf4j
@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
        log.debug("oAuth2Exception==>{}",oAuth2Exception);
        return ResponseEntity
//                .status(oAuth2Exception.getHttpErrorCode())
                .status(200)
                .body(new CustomOauthException(oAuth2Exception.getMessage()));
    }
}
