package kr.ranian.bootsecurity.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ranian129@gmail.com on 2019-10-18
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Controller
@Log
public class LoginController {

    @GetMapping("/login")
    public void login() {
        log.info("### /login called...");
    }

    @GetMapping("/accessDenied")
    public void accessDenied() {
        log.info("### /accessDenied called...");
    }

    @GetMapping("/logout")
    public void logout() {
        log.info("### /logout called...");
    }
}
