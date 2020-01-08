package cn.fast.web.mybatisplus.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * @author houqj
 * @date 2019-12-27 17:26
 */
public interface IBaseEnum<T extends Serializable> extends IEnum<T> {

    String getDesc();
}
