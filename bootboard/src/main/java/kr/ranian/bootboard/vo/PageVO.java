package kr.ranian.bootboard.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by ranian129@gmail.com on 2019-10-01
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public class PageVO {

    private static final int DEFAULT_SIZE = 10;
    public static final int DEFAULT_MAX_SIZE = 50;

    private int page;
    private int size;

    public PageVO() {
        this.page = 1;
        this.size = DEFAULT_SIZE;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page < 1 ? 1 : page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size < DEFAULT_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public Pageable makePageable(int direction, String... props) {
        Sort.Direction dir = direction == 0 ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(this.page - 1, this.size, dir, props);
    }
}
