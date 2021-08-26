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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    /**
     * 로그인
     */
    @GetMapping(value = "/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "member/loginForm";
    }


    /**
     * login하고 dashboad 페이지로 이동해야 함
     * 이때 dashboard에 현재 회원 id값 넘김
     */
    @PostMapping(value = "/login")
    public String login(@Valid LoginForm loginForm, BindingResult result,
                        RedirectAttributes rttr, HttpServletResponse response) throws IOException {
        if (result.hasErrors()){
            return "member/loginForm";
        }
        // 가입된 회원인지 체크하기
        boolean check = memberService.validateJoinedMember(loginForm.getEmail(), loginForm.getPassword());

        if (!check){ // 가입된 회원이면 dashboard로 이동함
            Long memberId = memberService.findByEmailAndPassword(loginForm.getEmail(),
                    loginForm.getPassword()).getId();
            rttr.addFlashAttribute("memberId", memberId);

        } else{ // 미가입 회원은 메시지
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 혹은 비밀번호가 일치하지 않습니다');history.go(-1); </script>");
            out.flush();
        }
        return "redirect:/dashboard";

    }


    /**
     *
     * 회원 가입
     */

    @GetMapping(value = "/create_account")
    public String registerForm(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "member/registForm";
    }

    @PostMapping(value = "/create_account")
    public String register(@Valid RegisterForm form, BindingResult result,
                           HttpServletResponse response) throws IOException {
        if (result.hasErrors()){
            return "member/registForm";
        }
        // 1차 비밀번호와 2차 비밀번호 확인
        if (!form.getPassword().equals(form.getCheckPassword())){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('두 비밀번호가 일치하지 않습니다.'); history.go(-1); </script>");
            out.flush();
        }
        else{
            // 가입된 회원인지 체크하기
            boolean check = memberService.validateJoinedMember(form.getEmail(), form.getPassword());
            if (check){ // 미가입 회원 -> 새 member 생성하고 홈화면으로 이동
                Member member = new Member(form.getEmail(), form.getPassword(), form.getMember_name(), MemberStatus.MEMBER);
                memberService.join(member);
            } else{  // 이미 가입한 회원 -> 메시지와 함께 홈화면으로 이동
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('이미 존재하는 회원입니다.'); history.go(-1); </script>");
                out.flush();
            }
        }

        return "redirect:/";
    }
}
