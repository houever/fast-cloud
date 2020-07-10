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
	 * 获取完整的异常栈信息
	 * @param t
	 * @return
	 */
	public static String getTrace(Throwable t) {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer= stringWriter.getBuffer();
		return buffer.toString();
	}

}
