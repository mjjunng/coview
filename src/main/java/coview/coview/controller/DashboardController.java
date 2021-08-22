package coview.coview.controller;

import coview.coview.domain.JoinMeeting;
import coview.coview.domain.Meeting;
import coview.coview.domain.Member;
import coview.coview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final MemberService memberService;

    /**
     *
     * 회의 목록 조회
     */
//    @GetMapping(value = "/dashboard")
//    public String dashboard(@PathVariable("memberId") Long memberId, Model model){
//        // 현재 회원의 id값을 통해서 회원이 참여하고 있는 meeting List 뿌려줌
//        Member findMember = memberService.findOne(memberId);
//        model.addAttribute("memberId", findMember.getId());
//        model.addAttribute("meetings", findMember.getMeetings());
//        return "/dashboard";
//    }

    /**
     * 회의 생성
     */
    @GetMapping(value = "/{memberId}/dashboard/new")
    public String createMeetingForm(Model model){
        model.addAttribute("form", new MeetingForm());
        return "/createMeetingForm";
    }

    /**
     * 회의 생성해서 받아오기
     */
    @PostMapping(value = "/{memberId}/dashboard/new")
    public String createMeeting(@Valid MeetingForm form, BindingResult result){
        if (result.hasErrors()){
            return "/createMeetingForm";
        }
        // 회의 생성하기
        Meeting meeting = new Meeting();
        //JoinMeeting...
        return "/dashboard";

    }
}
