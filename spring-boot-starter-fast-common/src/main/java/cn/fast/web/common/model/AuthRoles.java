package cn.fast.web.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRoles implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 角色代码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
}
