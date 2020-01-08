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
 * 系统部门表
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Data
@Entity
@Accessors(chain = true)
@ApiModel(value = "系统部门表",description = "系统部门表")
@EqualsAndHashCode(callSuper = false)
@TableName("sys_department")
public class SysDepartment extends BaseEntity{

private static final long serialVersionUID=1L;

            /**
     * 部门名称
     */
                    @ApiModelProperty(value = "部门名称",example = "部门名称")
    private String deptName;
            /**
     * 部门主管id
     */
                    @ApiModelProperty(value = "部门主管id",example = "部门主管id")
    private String leader;
            /**
     * 上级部门id
     */
                    @ApiModelProperty(value = "上级部门id",example = "上级部门id")
    private String parentId;
            /**
     * 排序编号
     */
                    @ApiModelProperty(value = "排序编号",example = "排序编号")
    private Integer sort;
            /**
     * 是否启用
     */
                    @ApiModelProperty(value = "是否启用",example = "是否启用")
    private Integer enabled;
            /**
     * 是否为父级节点
     */
                    @ApiModelProperty(value = "是否为父级节点",example = "是否为父级节点")
    private Boolean isParent;



        }
