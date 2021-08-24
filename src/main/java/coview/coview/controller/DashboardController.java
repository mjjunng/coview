package coview.coview.controller;

import coview.coview.domain.JoinMeeting;
import coview.coview.domain.Meeting;
import coview.coview.domain.Member;
import coview.coview.domain.MemberStatus;
import coview.coview.service.JoinMeetingService;
import coview.coview.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
    private final MemberService memberService;
    private final JoinMeetingService joinMeetingService;

    /**
     *
     * 회의 목록 조회
     */
    @GetMapping(value = "/dashboard")
    public String dashboard(HttpServletRequest request, Model model){
        // 현재 회원의 id값을 통해서 회원이 참여하고 있는 meeting List 뿌려줌
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        Long memberId = null;
        if (flashMap != null){
            memberId = (Long) flashMap.get("memberId");
        }
//        log.info("dashboard_memberId: " + memberId + "---");
        Member findMember = memberService.findOne(memberId);
        model.addAttribute("memberId", findMember.getId());
        model.addAttribute("meetings", findMember.getMeetings());
//        log.info("findMemberId: " + findMember.getId());
//        if (findMember.getMeetings().size() > 0){
//            log.info("meetings: " + findMember.getMeetings().get(0).getName());
//        }
        return "dashboard";
    }

    /**
     * 회의 생성 폼
     */
    @GetMapping(value = "/dashboard/new/{memberId}")
    public String createMeetingForm(@PathVariable("memberId") Long memberId,
                                    Model model){
        //log.info("memberId: " + memberId);
        model.addAttribute("meetingForm", new MeetingForm());
        model.addAttribute("memberId", memberId);
        return "createMeetingForm";
    }

    /**
     * 입력 폼으로부터 받은 데이터로 회의 생성하기
     */
    @PostMapping(value = "/dashboard/new")
    public String createMeeting(@RequestParam("memberId") Long memberId,
                                @Valid MeetingForm meetingForm, BindingResult result,
                                RedirectAttributes rttr){

        //log.info("----post mapping-----");
        if (result.hasErrors()){
            return "createMeetingForm";
        }
        // 회의 생성하기
        //log.info("memberId: " + memberId);
        log.info("meeting name : " + meetingForm.getName());
        joinMeetingService.createMeeting(memberId, meetingForm.getName());
        // dashboard로 갈 때 memberId 보내기
        rttr.addFlashAttribute("memberId", memberId);
        return "redirect:/dashboard";

    }
}
