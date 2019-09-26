package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.FreeBoard;
import kr.ranian.bootboard.domain.FreeBoardReply;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
public class FreeBoardRepositoryTest {

    @Autowired
    FreeBoardRepository boardRepo;

    @Autowired
    FreeBoardReplyRepository replyRepo;

    static {
        System.setProperty("jasypt.encryptor.password", "DEV2019");
    }

    @Test
    public void testInsertDummy() {
        IntStream.range(1, 200).forEach(i -> {
            FreeBoard board = new FreeBoard();
            board.setTitle("Free Board ... " + i);
            board.setContent("Free Content.... " + i);
            board.setWriter("user" + i%10);
            boardRepo.save(board);
        });
    }

    // 양방향 방식이 댓글 추가 - 복잡함.
    @Transactional
    @Test
    public void testInsertReply2Way() {

        Optional<FreeBoard> result = boardRepo.findById(199L);
        result.ifPresent(board -> {
            List<FreeBoardReply> replies = board.getReplies();

            FreeBoardReply reply = new FreeBoardReply();
            reply.setReply("REPLY..........");
            reply.setReplyer("replyer00");
            reply.setBoard(board);

            replies.add(reply);

            board.setReplies(replies);

            boardRepo.save(board);
        });
    }

    // 단방향 방식의 댓글 추가 - 더 간단함.
    @Test
    public void testInsertReply1Way() {

        FreeBoard board = new FreeBoard();
        board.setBno(199L);

        FreeBoardReply reply = new FreeBoardReply();
        reply.setReply("REPLY..........");
        reply.setReplyer("replyer00");
        reply.setBoard(board);

        replyRepo.save(reply);
    }

    @Test
    public void testPagingAndList1() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        boardRepo.findByBnoGreaterThan(0L, page).forEach(board -> {
            log.info(board.getBno() + ": " + board.getTitle());
        });

    }

    // 지연 로딩 문제 발생 : 모든 댓글 조회 쿼리가 추가되어 성능 저하됨.
    @Transactional
    @Test
    public void testPagingAndList2() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        boardRepo.findByBnoGreaterThan(0L, page).forEach(board -> {
            log.info(board.getBno() + ": " + board.getTitle() + ": " + board.getReplies().size());
        });
    }

    // 지연 로딩 문제 해결 : @Query로 조인 처리하여, 한 번에 필요한 데이터를 가져옴.
    @Test
    public void testPagingAndList3() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        boardRepo.getPage(page).forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }
}