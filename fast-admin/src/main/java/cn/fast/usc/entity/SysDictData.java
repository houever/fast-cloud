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
 * 系统分类数据表
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Data
@Entity
@Accessors(chain = true)
@ApiModel(value = "系统分类数据表",description = "系统分类数据表")
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict_data")
public class SysDictData extends BaseEntity{

private static final long serialVersionUID=1L;

            /**
     * 分类id
     */
                    @ApiModelProperty(value = "分类id",example = "分类id")
    private String dictId;
            /**
     * 分类名称(字典类型)
     */
                    @ApiModelProperty(value = "分类名称(字典类型)",example = "分类名称(字典类型)")
    private String type;
            /**
     * 键
     */
                    @ApiModelProperty(value = "键",example = "键")
    private String title;
            /**
     * 值
     */
                    @ApiModelProperty(value = "值",example = "值")
    private String val;
            /**
     * 排序
     */
                    @ApiModelProperty(value = "排序",example = "排序")
    private BigDecimal sort;
            /**
     * 备注
     */
                    @ApiModelProperty(value = "备注",example = "备注")
    private String remark;
            /**
     * 状态
     */
                    @ApiModelProperty(value = "状态",example = "状态")
    private Integer status;



        }
