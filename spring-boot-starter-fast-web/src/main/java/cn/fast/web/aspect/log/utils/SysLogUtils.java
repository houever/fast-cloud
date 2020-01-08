package cn.fast.web.aspect.log.utils;
import lombok.experimental.UtilityClass;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 系统日志工具类
 *
 * @author L.cm
 */
@UtilityClass
public class SysLogUtils {
	/**
	 * 获取客户端
	 *
	 * @return clientId
	 */
//	public String getClientId() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication instanceof OAuth2Authentication) {
//			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
//			return auth2Authentication.getOAuth2Request().getClientId();
//		}
//		return null;
//	}

	/**
	 * 获取用户名称
	 *
	 * @return username
	 */
//	public String getUsername() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null) {
//			return null;
//		}
//		return authentication.getName();
//	}

	/**
	 * 获取完整的异常栈信息
	 * @param t
	 * @return
	 */
	public static   String getTrace(Throwable t) {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer= stringWriter.getBuffer();
		return buffer.toString();
	}

}
