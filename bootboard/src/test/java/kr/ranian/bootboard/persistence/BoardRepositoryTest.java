package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}