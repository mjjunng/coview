package coview.coview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MeetingController {

    @GetMapping(value = "/meeting/{id}")
    public String enterMeeting(@PathVariable("id") Long joinMeetingId,
                               Model model){
        model.addAttribute("joinMeetingId", joinMeetingId);
        return "meeting/main";
    }

    // 호스트가 회의 멤버 초대하는 기능
    @PostMapping(value = "/meeting/{id}")
    public String inviteMember(@RequestParam("inviteEmail") String inviteEmail,
                             Model model){
        //log.info("invite: " + inviteEmail);
        return "meeting/main";
    }
}
