import com.tanhua.sso.MyApplication;
import com.tanhua.sso.service.SmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: ljs
 * @Pcakage: PACKAGE_NAME.TestSmsService
 * @Date: 2022年05月21日 19:13
 */
@SpringBootTest(classes = MyApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSmsService {

    @Autowired
    private SmsService smsService;

    @Test
    public void sendSms() {
        String code = this.smsService.sendSms("15654657444");
        System.out.println(code);
    }
}
