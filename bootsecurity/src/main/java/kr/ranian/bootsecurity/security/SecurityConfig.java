package kr.ranian.bootsecurity.security;

import kr.ranian.bootsecurity.domain.MemberRole;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by ranian129@gmail.com on 2019-10-17
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("build Auth global...");
        auth.inMemoryAuthentication()
                .withUser("manager")
                .password(passwordEncoder().encode("1111"))
                .roles(MemberRole.ROLE_MANAGER);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        log.info("### security config........");

        http.authorizeRequests().antMatchers("/guest/**").permitAll();

        http.authorizeRequests().antMatchers("/manager/**").hasRole(MemberRole.ROLE_MANAGER);

        http.authorizeRequests().antMatchers("/admin/**").hasRole(MemberRole.ROLE_ADMIN);

        http.formLogin().loginPage("/login");   // support to login with form tag.

        http.exceptionHandling().accessDeniedPage("/accessDenied");

        // 세션 무효화
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);
    }
}
