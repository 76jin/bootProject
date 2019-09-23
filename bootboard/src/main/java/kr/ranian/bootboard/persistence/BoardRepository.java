package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by ranian129@gmail.com on 2019-09-21
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface BoardRepository extends CrudRepository <Board, Long> {

    List<Board> findBoardByTitle(String title);

    Collection<Board> findByWriter(String writer);

    Collection<Board> findByWriterContaining(String writer);

    Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    // title LIKE % ? % AND bno > ?
    Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

    // bno > ? ORDER BY bno DESC
    Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
}
