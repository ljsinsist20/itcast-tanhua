package com.tanhua.sso.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.sso.vo.ErrorResult
 * @Date: 2022年05月21日 19:23
 */
@Data
@Builder
public class ErrorResult {
    private String errCode;
    private String errMessage;
}
