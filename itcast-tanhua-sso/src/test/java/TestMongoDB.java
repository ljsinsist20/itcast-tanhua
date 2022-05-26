import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * @author: ljs
 * @Pcakage: PACKAGE_NAME.TestMongoDB
 * @Date: 2022年05月25日 21:11
 * @Description:
 */
public class TestMongoDB {

    @Test
    public void testMongoDBData() {
        for (int i = 2; i < 100; i++) {
            int score = RandomUtils.nextInt(30, 99);
            System.out.println("db.recommend_user.insert({\"userId\":" + i +
                    ",\"toUserId\":1,\"score\":"+score+",\"date\":\"2019/1/1\"})");
        }
    }
}
