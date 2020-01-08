package cn.fast.web.common.model;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {

    private String id;
    private String name;
    private String code;
    private String path;
    private String component;
    private String icon;
    private String title;
    private boolean hidden;
    private String redirect;
    private boolean alwaysShow;
    private boolean enabled;
    private boolean keepalived;
    private String parentId;
    private Meta meta;
    private List<MenuVo> children;
}
