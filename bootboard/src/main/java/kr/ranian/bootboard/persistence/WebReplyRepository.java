package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.WebBoard;
import kr.ranian.bootboard.domain.WebReply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ranian129@gmail.com on 2019-10-04
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface WebReplyRepository extends CrudRepository<WebReply, Long> {

    @Query("select r from WebReply r where r.board = ?1 and r.rno > 0 order by r.rno asc ")
    List<WebReply> getRepliesObBoard(WebBoard board);
}
