package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.Board;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ranian129@gmail.com on 2019-09-21
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface BoardRepository extends CrudRepository <Board, Long> {
}
