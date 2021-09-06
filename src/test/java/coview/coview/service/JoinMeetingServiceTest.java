package coview.coview.service;

import coview.coview.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JoinMeetingServiceTest {
    @Autowired MemberService memberService;
    @Autowired JoinMeetingService joinMeetingService;
    @Autowired MeetingService meetingService;

    /**
     *
     * member1이 member2 초대
     */
    @Test
    public void 멤버초대() throws Exception {
        //given
//        Member member1 = new Member("666","000", "mem1", MemberStatus.MEMBER, Auth.LOGOUT);
//        Member member2 = new Member("777","123", "mem2", MemberStatus.MEMBER, Auth.LOGOUT);
//
//        memberService.join(member1);
//        memberService.join(member2);
//
//        //when
//        Long joinMeetingId = joinMeetingService.createMeeting(member1.getId(), "meeting1");
//        Long meetingId = joinMeetingService.inviteMember(member2.getEmail(), joinMeetingId);
//
//        //then
//        Assertions.assertThat(memberService.findTotalMember(meetingId).get(0)).isEqualTo(member1);
//        Assertions.assertThat(memberService.findTotalMember(meetingId).get(1)).isEqualTo(member2);
//        Meeting meeting = meetingService.findOne(meetingId);
//        Assertions.assertThat(meeting.getJoinMeetings().size()).isEqualTo(2);
//        Assertions.assertThat(meeting.getJoinMeetings().get(0).getMember().getEmail())
//                .isEqualTo(member1.getEmail());
//        Assertions.assertThat(meeting.getJoinMeetings().get(1).getMember().getEmail())
//                .isEqualTo(member2.getEmail());


    }

}