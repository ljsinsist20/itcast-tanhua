import com.tanhua.dubbo.server.DubboApplication;
import com.tanhua.dubbo.server.api.RecommendUserApi;
import com.tanhua.dubbo.server.pojo.RecommendUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: ljs
 * @Pcakage: PACKAGE_NAME.TestRecommendUserApi
 * @Date: 2022年05月25日 22:38
 * @Description:
 */
@SpringBootTest(classes = DubboApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRecommendUserApi {
    @Autowired
    private RecommendUserApi recommendUserApi;

    @Test
    public void testQueryWithMaxScore() {
        RecommendUser recommendUser = recommendUserApi.queryWithMaxScore(1L);
        System.out.println(recommendUser);
    }

    @Test
    public void testQueryPageInfo() {
        System.out.println(this.recommendUserApi.queryPageInfo(1L, 1, 5));
        System.out.println(this.recommendUserApi.queryPageInfo(1L, 2, 5));
        System.out.println(this.recommendUserApi.queryPageInfo(1L, 3, 5));
    }
}
