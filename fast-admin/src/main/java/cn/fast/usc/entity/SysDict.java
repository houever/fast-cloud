package cn.fast.usc.entity;
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
 * 系统字典分类表
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Data
@Entity
@Accessors(chain = true)
@ApiModel(value = "系统字典分类表",description = "系统字典分类表")
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict")
public class SysDict extends BaseEntity{

private static final long serialVersionUID=1L;

            /**
     * 分类名称
     */
                    @ApiModelProperty(value = "分类名称",example = "分类名称")
    private String title;
            /**
     * 排序
     */
                    @ApiModelProperty(value = "排序",example = "排序")
    private BigDecimal sort;
            /**
     * 类型
     */
                    @ApiModelProperty(value = "类型",example = "类型")
    private String type;
            /**
     * 备注
     */
                    @ApiModelProperty(value = "备注",example = "备注")
    private String remark;



        }
