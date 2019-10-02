package kr.ranian.bootboard.Controller;

import kr.ranian.bootboard.domain.WebBoard;
import kr.ranian.bootboard.persistence.WebBoardRepository;
import kr.ranian.bootboard.vo.PageMaker;
import kr.ranian.bootboard.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private WebBoardRepository repo;

/*
    @GetMapping("/list")
    public void list(@PageableDefault(
            direction = Sort.Direction.DESC,
            sort = "bno",
            size = 10,
            page = 0) Pageable page) {
        log.info("list() called..." + page);
    }
*/
    @GetMapping("/list")
    public void list(PageVO vo, Model model) {
        Pageable page = vo.makePageable(0, "bno");

        Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);

        log.info("" + page);
        log.info("" + result);

        log.info("Total Page Number:" + result.getTotalPages());

        model.addAttribute("result", new PageMaker<>(result));
    }
}
