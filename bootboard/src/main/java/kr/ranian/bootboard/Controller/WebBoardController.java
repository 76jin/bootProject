package kr.ranian.bootboard.Controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ranian129@gmail.com on 2019-09-28
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

    @GetMapping("/list")
    public void list() {
        log.info("list() called...");
    }
}
