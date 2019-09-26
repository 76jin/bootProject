package kr.ranian.bootboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ranian129@gmail.com on 2019-09-25
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Getter
@Setter
@ToString(exclude = "board")
@Entity
@Table(name = "tbl_free_replies", indexes = {@Index(unique = false, columnList = "board_bno")})
@EqualsAndHashCode(of = "rno")
public class FreeBoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String reply;
    private String replyer;

    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;

    @ManyToOne
    private FreeBoard board;
}
