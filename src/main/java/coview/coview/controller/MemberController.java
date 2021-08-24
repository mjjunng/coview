package coview.coview.controller;

import coview.coview.domain.Member;
import coview.coview.domain.MemberStatus;
import coview.coview.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "member/loginForm";
    }

    @PostMapping(value = "/login")
    public String login(@Valid LoginForm loginForm, BindingResult result, Model model,
                        RedirectAttributes rttr){
        if (result.hasErrors()){
            return "member/loginForm";
        }
        Member member = new Member(loginForm.getEmail(), loginForm.getPassword(), loginForm.getMember_name(), MemberStatus.MEMBER);
        Long memberId = memberService.join(member);
        //log.info("login_memberId: --" + memberId + "---");
        rttr.addFlashAttribute("memberId", memberId);
        return "redirect:/dashboard";

        /**
         * login하고 dashboad 페이지로 이동해야 함
         * 이때 dashboard에 현재 회원이 참여하고 있는 회의 목록 뿌려줌
         */
    }
}
