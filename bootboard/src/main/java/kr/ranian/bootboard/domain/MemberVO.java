package kr.ranian.bootboard.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by ranian129@gmail.com on 2019-09-26
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Data
@AllArgsConstructor
public class MemberVO {

    private int mno;
    private String mid;
    private String mpw;
    private String mname;
    private Timestamp regdate;
}
