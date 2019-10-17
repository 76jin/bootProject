package kr.ranian.bootboard.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ranian129@gmail.com on 2019-10-15
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface CustomWebBoard {

    public Page<Object[]> getCustomPage(String type, String keyword, Pageable page);
}
