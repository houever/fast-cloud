package cn.fast.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import cn.fast.web.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Data
@Entity
@Accessors(chain = true)
@ApiModel(value = "系统日志表", description = "系统日志表")
@EqualsAndHashCode(callSuper = false)
@TableName("sys_log")
public class SysLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 请求标题
     */
    @ApiModelProperty(value = "请求标题", example = "请求标题")
    private String title;
    /**
     * 日志类型
     */
    @ApiModelProperty(value = "日志类型", example = "日志类型")
    private Integer type;
    /**
     * 请求地址
     */
    @ApiModelProperty(value = "请求地址", example = "请求地址")
    private String requestUri;
    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法", example = "请求方法")
    private String method;
    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数", example = "请求参数")
    private String params;
    /**
     * 客户端来源
     */
    @ApiModelProperty(value = "客户端来源", example = "客户端来源")
    private String userAgent;
    /**
     * 服务id
     */
    @ApiModelProperty(value = "服务id", example = "服务id")
    private String serviceId;
    @ApiModelProperty(value = "", example = "")
    private String moduleName;
    @ApiModelProperty(value = "", example = "")
    private String exception;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", example = "用户名")
    private String username;
    /**
     * 请求ip
     */
    @ApiModelProperty(value = "请求ip", example = "请求ip")
    private String ip;
    /**
     * 归属地
     */
    @ApiModelProperty(value = "归属地", example = "归属地")
    private String ipAddress;
    /**
     * 执行时间
     */
    @ApiModelProperty(value = "执行时间", example = "执行时间")
    private Integer costTime;
    @ApiModelProperty(value = "", example = "")
    private Integer status;


}
