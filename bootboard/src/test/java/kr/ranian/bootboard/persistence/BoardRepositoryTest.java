package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by ranian129@gmail.com on 2019-09-21
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
//@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private  BoardRepository boardRepository;

    static {
        System.setProperty("jasypt.encryptor.password", "DEV2019");
    }

    @Test
    public void inspect() {
        // 실제 객체의 클래스 이름
        Class<?> clz = boardRepository.getClass();
        System.out.println(clz.getName());
        System.out.println("##########");

        // 클래스가 구현하고 있는 인터페이스 목록
        Class<?>[] interfaces = clz.getInterfaces();
        Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));
        System.out.println("##########");

        // 클래스의 부모 클래스
        Class<?> superClass = clz.getSuperclass();
        System.out.println(superClass.getName());
    }

    @Test
    public void testInsert() {
        Board board = new Board();
        board.setTitle("제목 테스트");
        board.setContent("내용 테스트");
        board.setWriter("user01");

        boardRepository.save(board);
    }

    @Test
    public void testRead() {
        Optional<Board> board = boardRepository.findById(1L);
        System.out.println(board);
    }

    @Test
    public void testUpdate() {
        System.out.println("Read first ..........");
        Optional<Board> board = boardRepository.findById(1L);

        System.out.println("Update title ..........");
        board.ifPresent(board1 -> board1.setTitle("제목 수정 테스트"));

        System.out.println("Call save() ..........");
        boardRepository.save(board.get());
    }

    @Test
    public void testDelete() {
        System.out.println("DELETE Test");
        boardRepository.deleteById(1L);
    }

    @Test
    public void testInsert200() {
        for (int i = 1; i <= 200; i++) {
            Board board = new Board();
            board.setTitle("제목.." + i);
            board.setContent("내용 ..." + i + " 채우기");
            board.setWriter("user0" + (i%10));
            boardRepository.save(board);
        }
    }

    @Test
    public void testByTitle() {

        boardRepository.findBoardByTitle("제목..177")
                .forEach(board -> System.out.println(board));
    }

    @Test
    public void testByWriter() {

        Collection<Board> results = boardRepository.findByWriter("user00");

        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void testByWriterContaining() {

        Collection<Board> results = boardRepository.findByWriterContaining("05");

        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void testByTitleContainingOrContentContaining() {
        Collection<Board> results = boardRepository.findByTitleContainingOrContentContaining("06", "07");

        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void testByTitleAndBno() {
        Collection<Board> results = boardRepository.findByTitleContainingAndBnoGreaterThan("5", 50L);

        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void testBnoOrderBy() {

        Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(90L);

        results.forEach(board -> System.out.println(board));
    }

    @Test
    public void testBnoOrderByPaging() {

        Pageable paging = PageRequest.of(0, 10);

        Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L, paging);

        results.forEach(board -> System.out.println(board));
    }
/*

    @Test
    public void testBnoPagingSort() {

        Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");

        Collection<Board> results = boardRepository.findByBnoGreaterThan(0L, paging);

        results.forEach(board -> System.out.println(board));
    }

*/
    @Test
    public void testBnoPagingSortAndPageTypeTest() {

        Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");

        Page<Board> result = boardRepository.findByBnoGreaterThan(0L, paging);

        System.out.println("PAGE SIZE:" + result.getSize());
        System.out.println("TOTAL PAGES:" + result.getTotalPages());
        System.out.println("TOTAL COUNT:" + result.getTotalElements());
        System.out.println("NEXT:" + result.nextPageable());

        List<Board> list = result.getContent();

        list.forEach(board -> System.out.println(board));
    }

    @Test
    public void testByTitleWithJPQL() {

        boardRepository.findByTitle("17")
                .forEach(board -> System.out.println(board));
    }

    @Test
    public void testByContentWithJPQL() {

        boardRepository.findByContent("18")
                .forEach(board -> System.out.println(board));
    }

    @Test
    public void testByWriterWithJPQL() {

        boardRepository.findByWriter("user09")
                .forEach(board -> System.out.println(board));
    }

    @Test
    public void testByTitle2() {

        boardRepository.findByTitle2("17")
                .forEach(object -> System.out.println(Arrays.toString(object)));
    }

    @Test
    public void testByTitle3() {

        boardRepository.findByTitle3("18")
                .forEach(objects -> System.out.println(Arrays.toString(objects)));
    }

    @Test
    public void testByPaging() {

        Pageable pageable = PageRequest.of(0, 10);

        boardRepository.findByPage(pageable)
                .forEach(board -> System.out.println(board));
    }
}