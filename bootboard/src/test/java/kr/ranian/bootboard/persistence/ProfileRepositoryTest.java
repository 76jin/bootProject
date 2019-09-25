package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.Member;
import kr.ranian.bootboard.domain.Profile;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by ranian129@gmail.com on 2019-09-25
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
@Log
@Commit
public class ProfileRepositoryTest {

    @Autowired
    MemberRepository memberRepo;

    @Autowired
    ProfileRepository profileRepo;

    static {
        System.setProperty("jasypt.encryptor.password", "DEV2019");
    }

    @Test
    public void testInsertMembers() {

        IntStream.range(1, 101).forEach(i -> {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUpw("pw" + i);
            member.setUname("사용자" + i);
            memberRepo.save(member);
        });
    }

    @Test
    public void testInsertProfile() {

        Member member = new Member();
        member.setUid("user1");

        for (int i = 1; i < 5; i++) {

            Profile profile = new Profile();
            profile.setFname("face" + i + ".jpg");

            if (i == 1) {
                profile.setCurrent(true);
            }

            profile.setMember(member);

            profileRepo.save(profile);
        }
    }

    @Test
    public void testFetchJoin1() {
        List<Object[]> result = memberRepo.getMemberWithProfileCount("user1");
        result.forEach(arr -> { System.out.println(Arrays.toString(arr));  });
    }

    @Test
    public void testFetchJoin2() {
        List<Object[]> result = memberRepo.getMemberWithProfile("user1");
        result.forEach(arr -> { System.out.println(Arrays.toString(arr)); });
    }
}