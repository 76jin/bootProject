package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.WebBoard;
import kr.ranian.bootboard.domain.WebReply;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by ranian129@gmail.com on 2019-10-04
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
@Log
@Commit
public class WebReplyRepositoryTest {

    @Autowired
    WebReplyRepository repo;

    static {
        System.setProperty("jasypt.encryptor.password", "DEV2019");
    }

    @Test
    public void testInsertReplies() {

        Long[] arr = {304L, 303L, 300L};

        Arrays.stream(arr).forEach(num -> {
            WebBoard board = new WebBoard();
            board.setBno(num);

            IntStream.range(0, 10).forEach(i -> {
                WebReply reply = new WebReply();
                reply.setReplyText("Reply ..." + i);
                reply.setReplyer("replyer" + i);
                reply.setBoard(board);

                repo.save(reply);
            });
        });
    }
}