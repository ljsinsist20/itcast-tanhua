import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author: ljs
 * @Pcakage: PACKAGE_NAME.Jasypt
 * @Date: 2022年05月22日 16:42
 */
public class Jasypt {
    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("666");
        System.out.println(encryptor.encrypt("YKtQ3DBdWm2vNPNCnFvvfy0MPBxlb5"));
    }
}
