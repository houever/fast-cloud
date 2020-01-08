package cn.fast.usc.entity;
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
 * 角色表
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Data
@Entity
@Accessors(chain = true)
@ApiModel(value = "角色表",description = "角色表")
@EqualsAndHashCode(callSuper = false)
@TableName("sys_roles")
public class SysRoles extends BaseEntity{

private static final long serialVersionUID=1L;

            /**
     * 角色代码
     */
                    @ApiModelProperty(value = "角色代码",example = "角色代码")
    private String code;
            /**
     * 角色名称
     */
                    @ApiModelProperty(value = "角色名称",example = "角色名称")
    private String name;
            /**
     * 是否设为默认角色
     */
                    @ApiModelProperty(value = "是否设为默认角色",example = "是否设为默认角色")
    private Integer def;
            /**
     * 备注
     */
                    @ApiModelProperty(value = "备注",example = "备注")
    private String remark;
            /**
     * 数据权限类型
     */
                    @ApiModelProperty(value = "数据权限类型",example = "数据权限类型")
    private Integer dataType;



        }
