package kr.ranian.bootboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ranian129@gmail.com on 2019-09-21
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;
    private String title;
    private String writer;
    private String content;

    @CreationTimestamp
    private Timestamp regdate;      // LocalDateTime
    @UpdateTimestamp
    private Timestamp updatedate;  // LocalDateTime
}
