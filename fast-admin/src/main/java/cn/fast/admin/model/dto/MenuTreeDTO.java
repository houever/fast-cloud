package cn.fast.admin.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MenuTreeDTO{


    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * vue加载路径
     */
    private String component;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否展示
     */
    private Integer alwaysShow;
    /**
     * 是否启用
     */
    private Integer enabled;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 菜单类型：0,菜单，1按钮
     */
    private Integer type;
    /**
     * 按钮类型
     */
    private String btnType;
    /**
     * 排序
     */
    private BigDecimal sort;

    private String id;
    private String title;
    private Integer level;
    /**
     * 是否展开
     */
    private boolean expand = true;
    /**
     * 是否禁用
     */
    private boolean disabled = false;
    /**
     * 禁掉 checkbox
     */
    private boolean disableCheckbox = true;
    /**
     * 是否选中子节点
     */
    private boolean selected = false;
    /**
     * 是否勾选
     */
    private boolean checked = false;
    private List<MenuTreeDTO> children;
}
