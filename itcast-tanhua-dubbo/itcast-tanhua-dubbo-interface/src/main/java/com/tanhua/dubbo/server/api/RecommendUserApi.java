package com.tanhua.dubbo.server.api;

import com.tanhua.dubbo.server.pojo.RecommendUser;
import com.tanhua.dubbo.server.vo.PageInfo;
import org.springframework.stereotype.Service;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.dubbo.server.api.t
 * @Date: 2022年05月25日 22:11
 * @Description:
 */
public interface RecommendUserApi {

    //查询一位得分最高的推荐用户
    RecommendUser queryWithMaxScore(Long userId);

    //按照得分倒序
    PageInfo<RecommendUser> queryPageInfo(Long userId, Integer pageNum, Integer pageSize);
}
