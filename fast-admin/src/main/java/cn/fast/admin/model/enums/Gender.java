package cn.fast.admin.model.enums;

import lombok.Getter;

/**
 * @author houqj
 * @date 2020-05-15 9:36
 */
@Getter
public enum Gender {

    MAIL("男性"),
    FEMAIL("女性");

    private String name;

    Gender(String name) {
        this.name = name;
    }
}
