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
 * 账户角色表
 * </p>
 *
 * @author HOU
 * @since 2020-01-07
 */
@Data
@Entity
@Accessors(chain = true)
@ApiModel(value = "账户角色表",description = "账户角色表")
@EqualsAndHashCode(callSuper = false)
@TableName("sys_account_roles")
public class SysAccountRoles extends BaseEntity{

private static final long serialVersionUID=1L;

            /**
     * 账户id
     */
                    @ApiModelProperty(value = "账户id",example = "账户id")
    private String aid;
            /**
     * 角色id
     */
                    @ApiModelProperty(value = "角色id",example = "角色id")
    private String rid;



        }
