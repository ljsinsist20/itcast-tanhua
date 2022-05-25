package com.tanhua.sso.pojo;

import com.tanhua.sso.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.pojo.UserInfo
 * @Date: 2022年05月21日 18:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends BasePojo{
    private Long userId;
    private String tags; //用户标签：多个用逗号分隔
    private SexEnum sex; //性别
    private Integer age; //年龄
    private String edu; //学历
    private String city; //城市
    private String birthday; //生日
    private String coverPic; // 封面图片
    private String industry; //行业
    private String income; //收入
    private String marriage; //婚姻状态

    private String nickName;//昵称
}
