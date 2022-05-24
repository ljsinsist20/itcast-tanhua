import com.tanhua.sso.MyApplication;
import com.tanhua.sso.service.FaceEngineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * @author: ljs
 * @Pcakage: PACKAGE_NAME.TestFaceEngineService
 * @Date: 2022年05月24日 22:23
 * @Description:
 */

@SpringBootTest(classes = MyApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestFaceEngineService {

    @Autowired
    private FaceEngineService faceEngineService;

    @Test
    public void testCheckIsPortrait() {
        File file = new File("E:\\黑马\\探花交友\\day01+探花交友-项目搭建以及登录完善个人信息\\day01\\资料\\测试图片\\t2.jpg");
        boolean b = faceEngineService.checkIsPortrait(file);
        System.out.println(b);
    }
}
