package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.PDSBoard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ranian129@gmail.com on 2019-09-25
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {

    @Modifying
    @Query("update from PDSFile f set f.pdsfile = ?2 where f.fno = ?1 ")
    int updatePDSFile(Long fno, String newFileName);

    @Modifying
    @Query("delete from PDSFile f where f.fno = ?1")
    int deletePDSFile(Long fno);

    @Query("select p, count(f) from PDSBoard p left outer join p.files f " +
            " on p.pid = f where p.pid > 0 group by p order by p.pid desc ")
    List<Object[]> getSummary();
}
