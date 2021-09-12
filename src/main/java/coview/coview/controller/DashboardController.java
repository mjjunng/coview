package coview.coview.controller;

import coview.coview.domain.*;
import coview.coview.repository.MemberRepositoryImpl;
import coview.coview.service.JoinMeetingService;
import coview.coview.service.MeetingService;
import coview.coview.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
    private final MemberService memberService;
    private final MeetingService meetingService;

    /**
     * 회의 목록 조회
     */
    @GetMapping(value = "/dashboard")
    public String dashboard(Authentication authentication, Model model){
        // 현재 회원의 id값을 통해서 회원이 참여하고 있는 meeting List 뿌려줌
        Member member = findNowMember(authentication);
        model.addAttribute("joinMeetings", member.getMeetings());


        return "dashboard";
    }

    /**
     * 회의 생성 폼
     */
    @GetMapping(value = "/dashboard/new")
    public String createMeetingForm(Model model){
        model.addAttribute("meetingForm", new MeetingForm());
        return "meeting/createMeetingForm";
    }

    /**
     * 입력 폼으로부터 받은 데이터로 회의 생성하기
     */
    @PostMapping(value = "/dashboard/new")
    public String createMeeting(@Valid MeetingForm meetingForm, BindingResult result,
                                Authentication authentication){

        if (result.hasErrors()){
            return "meeting/createMeetingForm";
        }
        // 회의 생성하기
        Member member = findNowMember(authentication);
        meetingService.createMeeting(member, meetingForm.getName());

        return "redirect:/dashboard";

    }

    public Long findNowMemberId(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = memberService.loadUserByUsername(userDetails.getUsername());
        return member.getId();
    }

    public Member findNowMember(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = memberService.loadUserByUsername(userDetails.getUsername());
        return member;
    }
}
