package kr.ranian.bootsecurity.controller;

import lombok.extern.java.Log;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ranian129@gmail.com on 2019-10-17
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Controller
@Log
public class SampleController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/guest")
    public void forGuest() {
        log.info("### guest");
    }

    @RequestMapping("/manager")
    public void forManager() {
        log.info("### manger");
    }

    @RequestMapping("/admin")
    public void forAdmin() {
        log.info("### admin");
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/adminSecret")
    public void forAdminSecret() {
        log.info("### adminSecret");
    }

    @Secured({"ROLE_MANAGER"})
    @RequestMapping("/managerSecret")
    public void forManagerSecret() {
        log.info("### managerSecret");
    }
}
