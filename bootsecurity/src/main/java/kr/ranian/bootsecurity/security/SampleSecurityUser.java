package kr.ranian.bootsecurity.security;

import kr.ranian.bootsecurity.domain.Member;
import kr.ranian.bootsecurity.domain.MemberRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ranian129@gmail.com on 2019-10-18
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Getter
@Setter
public class SampleSecurityUser extends User {

    public static final String ROLE_PREFIX = "ROLE_";

    private Member member;

    public SampleSecurityUser(Member member) {
//        super(member.getUid(), "{noop}" + member.getUpw(), makeGrantedAuthority(member.getRoles()));
        super(member.getUid(), member.getUpw(), makeGrantedAuthority(member.getRoles()));
        this.member = member;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {
        List<GrantedAuthority> list = new ArrayList<>();

        roles.forEach(role -> {
            list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName()));
        });

        return list;
    }

    public SampleSecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
