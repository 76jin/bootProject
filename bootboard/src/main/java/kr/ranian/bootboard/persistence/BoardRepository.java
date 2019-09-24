package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by ranian129@gmail.com on 2019-09-21
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface BoardRepository extends CrudRepository <Board, Long>, QuerydslPredicateExecutor<Board> {

    List<Board> findBoardByTitle(String title);

//    Collection<Board> findByWriter(String writer);

    Collection<Board> findByWriterContaining(String writer);

    Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    // title LIKE % ? % AND bno > ?
    Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

    // bno > ? ORDER BY bno DESC
    Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    // bno > ? ORDER BY bno DESC limit ?, ?
    List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);

    // bno > ? ORDER BY bno ASC/DESC limit ?, ?
//    List<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    // return Page<T> : bno > ? ORDER BY bno ASC/DESC limit ?, ?
    Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    @Query("select b from Board b where b.title like %?1% and b.bno > 0 order by b.bno desc")
    List<Board> findByTitle(String title);

    @Query("select b from Board b where b.content like %:content% and b.bno > 0 order by b.bno desc")
    List<Board> findByContent(@Param("content") String content);

    @Query("select b from #{#entityName} b where b.writer like %?1% and b.bno > 0 order by b.bno desc ")
    List<Board> findByWriter(String writer);

    @Query("select b.bno, b.title, b.writer, b.regdate " +
            " from Board b where b.title like %?1% and b.bno > 0 order by  b.bno desc ")
    List<Object[]> findByTitle2(String title);

    @Query(value = "select bno, title, writer from tbl_boards where title like CONCAT('%', ?1, '%') and bno > 0 order by bno desc",
        nativeQuery = true)
    List<Object[]> findByTitle3(String title);

    @Query("select b from Board b where b.bno > 0 order by b.bno desc ")
    List<Board> findByPage(Pageable pageable);
}
