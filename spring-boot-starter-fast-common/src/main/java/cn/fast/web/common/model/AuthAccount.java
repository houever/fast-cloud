package cn.fast.web.common.model;

import lombok.Data;

import java.util.List;

/**
 * @author houqj
 * @date 2020-01-07 10:51
 */
@Data
public class AuthAccount {
    /**
     * 主键
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 密码
     */
    private String password;
    /**
     * 电话
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 个人简介
     */
    private String introduction;
    /**
     * 地址
     */
    private String address;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 部门id
     */
    private String deptId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码强度
     */
    private String passStrengh;
    /**
     * 账户类型
     */
    private Integer type;
    /**
     * 账户是否启用
     */
    private boolean enabled;
    /**
     * 账户没有超时
     */
    private boolean accountNonExpired;
    /**
     * 账户是否被锁定
     */
    private boolean accountNonLocked;
    /**
     * 凭证是否超时
     */
    private boolean credentialsNonExpired;

    private List<String> roles;

    private List<MenuVo> menus;

    private List<String> btns;
}
