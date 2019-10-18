package kr.ranian.bootsecurity.controller;

import kr.ranian.bootsecurity.domain.Member;
import kr.ranian.bootsecurity.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ranian129@gmail.com on 2019-10-18
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Controller
@Log
@RequestMapping("/member/")
public class MemberController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository repo;

    @GetMapping("/join")
    public void join() {
        log.info("### join...");
    }

    @Transactional
    @PostMapping("/join")
    public String joinPost(@ModelAttribute("member") Member member) {
        log.info("### Member:" + member);

        String encryptString = passwordEncoder.encode(member.getUpw());
        log.info("### en:" + encryptString);
        member.setUpw(encryptString);
        repo.save(member);

        return "/member/joinResult";
    }
}
