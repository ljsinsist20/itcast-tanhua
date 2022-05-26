package com.tanhua.dubbo.server.api;


import com.tanhua.dubbo.server.pojo.RecommendUser;
import com.tanhua.dubbo.server.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ljs
 * @Pcakage: com.tanhua.dubbo.server.api.RecommendUserApiImpl
 * @Date: 2022年05月25日 22:23
 * @Description:
 */
//@Service(version = "1.0.0")
@Service
public class RecommendUserApiImpl implements RecommendUserApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public RecommendUser queryWithMaxScore(Long userId) {
        Query query = Query.query(Criteria.where("toUserId").is(userId)).with(Sort.by(Sort.Order.desc("score"))).limit(1);
        return mongoTemplate.findOne(query, RecommendUser.class);
    }

    @Override
    public PageInfo<RecommendUser> queryPageInfo(Long userId, Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Order.desc("score")));
        Query query = Query.query(Criteria.where("toUserId").is(userId)).with(pageRequest);
        List<RecommendUser> recommendUserList = mongoTemplate.find(query, RecommendUser.class);
        return new PageInfo<>(0, pageNum, pageSize, recommendUserList);
    }
}
