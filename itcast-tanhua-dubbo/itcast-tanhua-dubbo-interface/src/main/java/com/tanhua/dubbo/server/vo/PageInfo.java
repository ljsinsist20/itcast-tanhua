package com.tanhua.dubbo.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.dubbo.server.vo.PageInfo
 * @Date: 2022年05月25日 22:18
 * @Description:
 */
@Data
@AllArgsConstructor
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = -4809452852918392488L;

    /**
     * 总条数
     */
    private Integer total;
    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 一页显示的大小
     */
    private Integer pageSize;

    private List<T> records = Collections.emptyList();

}
