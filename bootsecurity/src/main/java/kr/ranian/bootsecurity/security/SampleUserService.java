package kr.ranian.bootsecurity.security;

import kr.ranian.bootsecurity.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by ranian129@gmail.com on 2019-10-18
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Service
@Log
public class SampleUserService implements UserDetailsService {

    @Autowired
    MemberRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("### loadUserByUsername called...");

//        User user = new User(username, "{noop}1111", Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")));

//        repo.findById(username)
//                .ifPresent(member -> log.info("### " + member));

        User user = repo.findById(username)
                .filter(member -> member != null)
                .map(member -> new SampleSecurityUser(member))
                .get();

        return user;
    }
}
