package cn.fast.web.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author houqj
 * @date 2019-12-27 15:59
 */
@Getter
public enum GenderEnum implements IBaseEnum<Integer> {

    MEN(1,"男"),
    WOMEN(0,"女");

    @EnumValue
    private final Integer value;
    private String desc;

    GenderEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.getDesc();
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
