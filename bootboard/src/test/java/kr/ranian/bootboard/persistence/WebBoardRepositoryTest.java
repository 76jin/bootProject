package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.WebBoard;
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

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by ranian129@gmail.com on 2019-09-28
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
@Log
@Commit
public class WebBoardRepositoryTest {

    @Autowired
    WebBoardRepository repo;

    static {
        System.setProperty("jasypt.encryptor.password", "DEV2019");
    }

    @Test
    public void testInsertBoardDummies() {
        IntStream.range(0, 300).forEach(i -> {
            WebBoard board = new WebBoard();
            board.setTitle("Sample Board Title " + i);
            board.setContent("Content Sample ..." + i + " of Board ");
            board.setWriter("user0" + (i % 10));

            repo.save(board);
        });
    }

    @Test
    public void testList1() {

        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "bno");

        Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), pageable);

        log.info("PAGE:" + result.getPageable());

        log.info("----------------------------");
        result.getContent().forEach(webBoard -> log.info("" + webBoard));
    }

    @Test
    public void testList2() {

        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "bno");

        Page<WebBoard> result = repo.findAll(repo.makePredicate("t", "10"), pageable);

        log.info("PAGE:" + result.getPageable());

        log.info("----------------------------");
        result.getContent().forEach(webBoard -> log.info("" + webBoard));
    }
}