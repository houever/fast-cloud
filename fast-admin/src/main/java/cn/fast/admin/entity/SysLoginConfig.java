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
 * 登录背景配置表
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Data
@Entity
@Accessors(chain = true)
@ApiModel(value = "登录背景配置表",description = "登录背景配置表")
@EqualsAndHashCode(callSuper = false)
@TableName("sys_login_config")
public class SysLoginConfig extends BaseEntity{

private static final long serialVersionUID=1L;

            /**
     * 背景视频
     */
                    @ApiModelProperty(value = "背景视频",example = "背景视频")
    private String bgVideo;
            /**
     * 背景webm
     */
                    @ApiModelProperty(value = "背景webm",example = "背景webm")
    private String bgWebm;
            /**
     * 背景图片
     */
                    @ApiModelProperty(value = "背景图片",example = "背景图片")
    private String bgImg;
            /**
     * 是否是背景
     */
                    @ApiModelProperty(value = "是否是背景",example = "是否是背景")
    private Integer isBg;



        }
