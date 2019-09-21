package kr.ranian.bootboard.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ranian129@gmail.com on 2019-09-21
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
public class JasyptTest {

    private String ENCRYPT_KEY = "ENCRYPT_KEY";

    @Test
    public void 암호화복호화Test() throws Exception {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(ENCRYPT_KEY);         // 암호화 key
        encryptor.setAlgorithm("PBEWithMD5AndDES"); // 암호화 알고리즘
        encryptor.setStringOutputType("base64");

        String encryptedText = encryptor.encrypt("DB_Password");    // 암호화
        String plainText = encryptor.decrypt(encryptedText);        // 복호화

        System.out.println("encryptedText:" + encryptedText);
        System.out.println("plainText:" + plainText);
    }

}
