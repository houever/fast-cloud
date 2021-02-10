package cn.fast.web.aspect.log.event;

import cn.fast.web.aspect.log.model.SysLog;
import cn.fast.web.common.constant.ServiceConstant;
import cn.fast.web.common.result.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.SQLException;


/**
 * @author houqijun
 * 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

	@Resource
	RestTemplate restTemplate;

	@Value("logservice")
	private String logservice;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) throws SQLException {
		SysLog sysLog = (SysLog) event.getSource();
		log.debug("系统日志:{}",sysLog);
		ResponseEntity<Result> resultResponseEntity = restTemplate.postForEntity("http://"+logservice+"/sys-log/add", HttpMethod.POST, Result.class);
		log.debug("调用日志服务，持久化到数据库========>{}",resultResponseEntity);
	}
}
