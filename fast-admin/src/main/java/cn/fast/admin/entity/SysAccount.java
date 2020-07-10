package cn.fast.admin.entity;

import cn.fast.admin.model.enums.Gender;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Data
@Entity
@Accessors(chain = true)
@ApiModel(value = "账户表", description = "账户表")
@EqualsAndHashCode(callSuper = false)
@TableName("sys_account")
public class SysAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", example = "用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", example = "密码")
    private String password;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", example = "电话")
    private String phone;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", example = "头像")
    private String avatar;
    /**
     * 个人简介
     */
    @ApiModelProperty(value = "个人简介", example = "个人简介")
    private String introduction;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址", example = "地址")
    private String address;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", example = "性别")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id", example = "部门id")
    private String deptId;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", example = "邮箱")
    private String email;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", example = "昵称")
    private String nickName;
    /**
     * 密码强度
     */
    @ApiModelProperty(value = "密码强度", example = "密码强度")
    private String passStrengh;
    /**
     * 账户类型
     */
    @ApiModelProperty(value = "账户类型", example = "账户类型")
    private Integer type;
    /**
     * 账户是否启用
     */
    @ApiModelProperty(value = "账户是否启用", example = "账户是否启用")
    private Integer enabled;
    /**
     * 账户没有超时
     */
    @ApiModelProperty(value = "账户没有超时", example = "账户没有超时")
    private Integer accountNonExpired;
    /**
     * 账户是否被锁定
     */
    @ApiModelProperty(value = "账户是否被锁定", example = "账户是否被锁定")
    private Integer accountNonLocked;
    /**
     * 凭证是否超时
     */
    @ApiModelProperty(value = "凭证是否超时", example = "凭证是否超时")
    private Integer credentialsNonExpired;
    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id", example = "租户id")
    private String tenantId;


}
