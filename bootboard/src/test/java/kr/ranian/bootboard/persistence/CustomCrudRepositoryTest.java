package kr.ranian.bootboard.persistence;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by ranian129@gmail.com on 2019-10-15
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
@Log
@Commit
public class CustomCrudRepositoryTest {

    @Autowired
    CustomCrudRepository repo;

    static {
        System.setProperty("jasypt.encryptor.password", "DEV2019");
    }

    @Test
    public void test1() {
        Pageable pageable  = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        String tyep = "t";
        String keyword = "10";

        Page<Object[]> result = repo.getCustomPage(tyep, keyword, pageable);

        log.info("" + result);
        log.info("Total Pages:" + result.getTotalPages());
        log.info("Total Size:" + result.getTotalElements());

        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }

    @Test
    public void testWriter() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        String type = "w";
        String keyword = "user09";

        Page<Object[]> result = repo.getCustomPage(type, keyword, pageable);

        log.info("" + result);
        log.info("Total Pages:" + result.getTotalPages());
        log.info("Total Size:" + result.getTotalElements());

        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }
}