package kr.ranian.bootsecurity.security;

import kr.ranian.bootsecurity.domain.MemberRole;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Created by ranian129@gmail.com on 2019-10-17
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    // 3. use custom class
    @Autowired
    SampleUserService sampleUserService;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
/*
    // 1. use inMemory
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("build Auth global...");
        auth.inMemoryAuthentication()
                .withUser("manager")
                .password(passwordEncoder().encode("1111"))
                .roles(MemberRole.ROLE_MANAGER);
    }
*/
/*

    // 2. use JDBC
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("build Auth global...");

        String query1 = "select uid username, upw password, true enabled from tbl_test_members where uid = ?";
        String query2 = "select member uid, role_name role from tbl_test_member_roles where member = ?";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery(query1)
                .rolePrefix("ROLE_")
                .authoritiesByUsernameQuery(query2);
    }
*/

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

//        http.userDetailsService(sampleUserService);
//        http.rememberMe().key("sample").userDetailsService(sampleUserService);
        http.rememberMe().key("sample").userDetailsService(sampleUserService)
                .tokenRepository(getJDBCRepository())
                .tokenValiditySeconds(60 * 60 * 24);    // cookie expired : 24 hours
    }

    private PersistentTokenRepository getJDBCRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }


}
