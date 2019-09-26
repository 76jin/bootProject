package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.FreeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ranian129@gmail.com on 2019-09-25
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {

    List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);

    // 지연로딩 문제 해결
    @Query("select b.bno, b.title, count(r) " +
            " from FreeBoard b left outer join b.replies r " +
            " where b.bno > 0 group by b ")
    List<Object[]> getPage(Pageable page);
}
