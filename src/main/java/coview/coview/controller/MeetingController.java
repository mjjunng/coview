package coview.coview.controller;

import coview.coview.domain.Meeting;
import coview.coview.domain.Member;
import coview.coview.domain.MemberStatus;
import coview.coview.repository.MemberRepository;
import coview.coview.service.JoinMeetingService;
import coview.coview.service.MeetingService;
import coview.coview.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MeetingController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final MeetingService meetingService;
    private final JoinMeetingService joinMeetingService;

    @GetMapping(value = "/meeting/{id}")
    public String enterMeeting(@PathVariable("id") Long meetingId,
                               Model model){
        model.addAttribute("meetingId", meetingId);
        return "meeting/meeting";
    }

    // 호스트가 회의 멤버 초대하는 기능
    @PostMapping(value="/meeting/{id}")
    public String invite(@RequestParam("inviteEmail") String inviteEmail,
                         @RequestParam("meetingId") Long meetingId,
                         HttpServletResponse response) throws IOException {

        // 가입된 회원인지 체크하기
        Optional<Member> byEmail = memberRepository.findByEmail(inviteEmail);
        if (byEmail.isPresent()){
            meetingService.inviteMember(byEmail.get(), meetingId);
        } else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('존재하지 않는 회원입니다.'); history.go(-1); </script>");
            out.flush();
        }
        return "meeting/meeting";

    }

}
