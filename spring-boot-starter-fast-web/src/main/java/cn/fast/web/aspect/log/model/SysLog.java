package cn.fast.web.aspect.log.model;

import cn.fast.web.common.utils.SnowFlakeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author HOU
 * @since 2019-03-03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysLog {

    private static final long serialVersionUID = 1L;

    private String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());

    /**
     * 请求标题
     */
    private String title;
    /**
     * 日志类型
     */
    private Integer type;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求地址
     */
    private String requestUri;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 客户端来源
     */
    private String userAgent;
    /**
     * 服务id
     */
    private String serviceId;
    private String moduleName;
    /**
     * 用户名
     */
    private String username;

    /**
     * 请求状态
     */
    private Integer status;
    /**
     * 请求ip
     */
    private String ip;
    /**
     * 归属地
     */
    private String ipAddress;
    /**
     * 执行时间
     */
    private Long costTime;
    /**
     * 异常信息
     */
    private String exception;



}
