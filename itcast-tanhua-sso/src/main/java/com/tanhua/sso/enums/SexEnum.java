package com.tanhua.sso.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.enums.SexEnum
 * @Date: 2022年05月21日 18:04
 */
public enum SexEnum implements IEnum<Integer> {

    MAN(1, "男"),
    WOMAN(2,"女"),
    UNKNOWN(3,"未知");

    private int values;
    private String desc;

    SexEnum(int values, String desc) {
        this.values = values;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.values;
    }


    @Override
    public String toString() {
        return this.desc;
    }
}
