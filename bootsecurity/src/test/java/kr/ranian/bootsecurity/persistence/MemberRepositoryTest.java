package kr.ranian.bootsecurity.persistence;

import kr.ranian.bootsecurity.domain.Member;
import kr.ranian.bootsecurity.domain.MemberRole;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by ranian129@gmail.com on 2019-10-17
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
@Log
@Commit
public class MemberRepositoryTest {

    static {
        System.setProperty("jasypt.encryptor.password", "DEV2019");
    }

    @Autowired
    private MemberRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUpw("pw" + i);
            member.setUname("사용자" + i);

            MemberRole role = new MemberRole();
            if (i <= 80) {
                role.setRoleName("BASIC");
            } else if (i <= 90) {
                role.setRoleName("MANAGER");
            } else {
                role.setRoleName("ADMIN");
            }

            member.setRoles(Arrays.asList(role));
            repo.save(member);
        }
    }

    @Test
    public void testRead() {
        Optional<Member> result = repo.findById("user85");

        result.ifPresent(member -> log.info("### member:" + member));
    }

    @Test
    public void testUpdateEncryptPassword() {

        List<String> ids = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            ids.add("user" + i);
        }

        repo.findAllById(ids).forEach(member -> {
            member.setUpw(passwordEncoder.encode(member.getUpw()));
            repo.save(member);
        });
    }
}