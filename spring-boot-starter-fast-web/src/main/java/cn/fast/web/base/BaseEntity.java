package cn.fast.web.base;

import cn.fast.web.common.utils.SnowFlakeUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 *  cn.fast.entity
 * @Description: 基础实体类
 * @date 2018/4/12 14:00
 */
@Data
@MappedSuperclass
@ApiModel(value = "基础实体")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class BaseEntity extends Model implements Serializable {

    @Id
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键id")
    protected String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());

    /**
     * 是否已删除 0：未删除 1：已删除
     */
    //@TableLogic(value = "0", delval = "1")
    @ApiModelProperty(value = "是否已删除:0 未删除，1已删除",hidden = true)
    protected Integer deleted = 0;

    /*创建人*/
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人",hidden = true)
    protected String createBy;

    /*创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间",hidden = true)
    protected Date createTime;

    /*修改人*/
    @TableField(value ="update_by",fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "修改人")
    protected String updateBy;

    /*修改时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    @ApiModelProperty(value = "修改时间",hidden = true)
    protected Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
