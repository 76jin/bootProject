package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ranian129@gmail.com on 2019-10-15
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface CustomCrudRepository extends CrudRepository<WebBoard, Long>, CustomWebBoard {
}
