package com.tanhua.sso.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.pojo.BasePojo
 * @Date: 2022年05月21日 18:14
 */
public abstract class BasePojo {

    @TableField(fill =  FieldFill.INSERT)
    private Date created;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updated;
}
