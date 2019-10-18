package kr.ranian.bootsecurity.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by ranian129@gmail.com on 2019-10-17
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_test_member_roles")
@EqualsAndHashCode(of = "fno")
@ToString
public class MemberRole {

    public static final String ROLE_GUEST = "GUEST";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private String roleName;
}
