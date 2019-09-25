package kr.ranian.bootboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ranian129@gmail.com on 2019-09-24
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_members")
@EqualsAndHashCode(of = "uid")
public class Member {

    @Id
    private String uid;
    private String upw;
    private String uname;
}
