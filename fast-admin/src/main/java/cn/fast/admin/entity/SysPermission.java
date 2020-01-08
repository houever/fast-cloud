package cn.fast.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * 权限菜单表
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Data
@Entity
@Accessors(chain = true)
@ApiModel(value = "权限菜单表", description = "权限菜单表")
@EqualsAndHashCode(callSuper = false)
@TableName("sys_permission")
public class SysPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", example = "菜单名称")
    private String name;
    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径", example = "菜单路径")
    private String path;
    /**
     * vue加载路径
     */
    @ApiModelProperty(value = "vue加载路径", example = "vue加载路径")
    private String component;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标", example = "菜单图标")
    private String icon;
    /**
     * 菜单标题（英文）
     */
    @ApiModelProperty(value = "菜单标题（英文）", example = "菜单标题（英文）")
    private String title;
    /**
     * 是否隐藏
     */
    @ApiModelProperty(value = "是否隐藏", example = "是否隐藏")
    private Integer hidden;
    /**
     * 重定向地址
     */
    @ApiModelProperty(value = "重定向地址", example = "重定向地址")
    private String redirect;
    /**
     * 是否展示
     */
    @ApiModelProperty(value = "是否展示", example = "是否展示")
    private Integer alwaysShow;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用", example = "是否启用")
    private Integer enabled;
    /**
     * 是否缓存
     */
    @ApiModelProperty(value = "是否缓存", example = "是否缓存")
    private Integer keepalived;
    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id", example = "父节点id")
    private String parentId;
    /**
     * 菜单类型（0：菜单，1按钮）
     */
    @ApiModelProperty(value = "菜单类型（0：菜单，1按钮）", example = "菜单类型（0：菜单，1按钮）")
    private Integer type;
    /**
     * 按钮权限类型
     */
    @ApiModelProperty(value = "按钮权限类型", example = "按钮权限类型")
    private String btnType;
    /**
     * 按钮代码
     */
    @ApiModelProperty(value = "按钮代码", example = "按钮代码")
    private String btnCode;
    /**
     * 排序值
     */
    @ApiModelProperty(value = "排序值", example = "排序值")
    private BigDecimal sort;
    @ApiModelProperty(value = "", example = "")
    private Integer level;


}
