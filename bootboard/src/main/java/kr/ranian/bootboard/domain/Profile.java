package kr.ranian.bootboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by ranian129@gmail.com on 2019-09-24
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Getter
@Setter
@ToString(exclude = "member")
@Entity
@Table(name = "tbl_profile")
@EqualsAndHashCode(of = "fname")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;
    private String fname;
    private boolean current;

    @ManyToOne
    private Member member;
}
