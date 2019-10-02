package kr.ranian.bootboard.persistence;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import kr.ranian.bootboard.domain.QWebBoard;
import kr.ranian.bootboard.domain.WebBoard;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ranian129@gmail.com on 2019-09-28
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface WebBoardRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard> {

    default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        QWebBoard board = QWebBoard.webBoard;

        // type if ~ else
        if (type == null) {
            return builder;
        }

        switch (type) {
            case "t":
                builder.and(board.title.like("%" + keyword + "%"));
                break;
            case "c":
                builder.and(board.content.like("%" + keyword + "%"));
                break;
            case "w":
                builder.and(board.writer.like("%" + keyword + "%"));
                break;
        }

        // bno > 0
        builder.and(board.bno.gt(0));
        return builder;
    }
}
