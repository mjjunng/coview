package coview.coview.controller;

import coview.coview.domain.JoinMeeting;
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
import java.util.ArrayList;
import java.util.List;
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
        Meeting one = meetingService.findOne(meetingId);
        List<JoinMeeting> joinMeetings = one.getJoinMeetings();
        List<Member> members = new ArrayList<>();
        for(int i=0; i<one.getCnt(); i++){
            members.add(joinMeetings.get(i).getMember());
        }
        model.addAttribute("meetingId", meetingId);
        // 현재 회의에 참여중인 멤버 뿌리기
        model.addAttribute("members", members);

        return "meeting/meeting";
    }

    // 호스트가 회의 멤버 초대하는 기능
    @PostMapping(value="/meeting/{id}")
    public String invite(@RequestParam("inviteEmail") String inviteEmail,
                         @RequestParam("meetingId") Long meetingId,
                         HttpServletResponse response) throws IOException {
        Optional<Member> byEmail = memberRepository.findByEmail(inviteEmail);

        // 이미 초대된 회원인지 체크하기
        // joinMeeting에 해당 meetingId를 가진 memberId가 있을 때
        List<JoinMeeting> byMeetingId = joinMeetingService.findByIds(meetingId, byEmail.get().getId());
        if(byMeetingId.size() > 0){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('이미 초대한 회원입니다.'); history.go(-1); </script>");
            out.flush();
        }

        // 가입된 회원인지 체크하기
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

    @GetMapping(value = "/test")
    public String testPage(Model model){
        return "meeting/meeting";
    }


}
