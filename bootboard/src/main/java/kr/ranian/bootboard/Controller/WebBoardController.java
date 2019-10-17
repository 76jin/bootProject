package kr.ranian.bootboard.Controller;

import kr.ranian.bootboard.domain.WebBoard;
import kr.ranian.bootboard.persistence.CustomCrudRepository;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//    private WebBoardRepository repo;
    private CustomCrudRepository repo;

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
    @GetMapping("/register")
    public void registerGet(@ModelAttribute("vo") WebBoard vo) {
        log.info("register get");
    }
/*
    @GetMapping("/list")
    public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {
        Pageable page = vo.makePageable(0, "bno");

        Page<WebBoard> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword()), page);

        log.info("" + page);
        log.info("" + result);

        log.info("Total Page Number:" + result.getTotalPages());

        model.addAttribute("result", new PageMaker<>(result));
    }
*/
    @GetMapping("/list")
    public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {
        Pageable page = vo.makePageable(0, "bno");

        Page<Object[]> result = repo.getCustomPage(vo.getType(), vo.getKeyword(), page);

        log.info("" + page);
        log.info("" + result);

        log.info("Total Page Number:" + result.getTotalPages());

        model.addAttribute("result", new PageMaker<>(result));
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("vo") WebBoard vo, RedirectAttributes rttr) {
        log.info("register post");
        log.info(" " + vo);

        repo.save(vo);
        rttr.addFlashAttribute("msg", "success");

        return "redirect:/boards/list";
    }

    @GetMapping("/view")
    public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("Bno:" + bno);

        repo.findById(bno).ifPresent(webBoard -> model.addAttribute("vo", webBoard));
    }

    @GetMapping("/modify")
    public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("Modify BNO:" + bno);

        repo.findById(bno).ifPresent(webBoard -> model.addAttribute("vo", webBoard));
    }

    @PostMapping("/delete")
    public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {
        log.info("Delete BNO:" + bno);

        repo.deleteById(bno);

        rttr.addFlashAttribute("msg", "success");

        // 페이징과 검색 결과로 이동하는 경우
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/list";
    }

    @PostMapping("/modify")
    public String modifyPost(WebBoard board, PageVO vo, RedirectAttributes rttr) {
        log.info("Modify WebBoard:" + board);

        repo.findById(board.getBno()).ifPresent(origin -> {
            origin.setTitle(board.getTitle());
            origin.setContent(board.getContent());

            repo.save(origin);

            rttr.addFlashAttribute("msg", "success");

            rttr.addAttribute("bno", origin.getBno());
        });

        // 페이징과 검색 결과로 이동하는 경우
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/list";
    }
}
