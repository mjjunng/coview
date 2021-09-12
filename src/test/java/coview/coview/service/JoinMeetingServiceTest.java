package coview.coview.service;

import coview.coview.domain.*;
import coview.coview.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ContextConfiguration
public class JoinMeetingServiceTest {
    @Autowired MemberService memberService;
    @Autowired JoinMeetingService joinMeetingService;
    @Autowired MeetingService meetingService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MockMvc mockMvc;

    /**
     *
     * member1이 member2 초대
     */
    @Test
    public void 멤버초대() throws Exception {

    }

    @Test
    @WithMockUser("testUser")
    public void 참여중인회원의조인미팅출력() throws Exception {
        //given
        // == 멤버 생성 ==
        MemberInfoDto infoDto1 = new MemberInfoDto("111", "111", "testUSer1");
        Long memberId1 = memberService.save(infoDto1);

        MemberInfoDto infoDto2 = new MemberInfoDto("999", "999", "testUSer2");
        Long memberId2 = memberService.save(infoDto2);


        // == 미팅 생성 ==
        Meeting meeting = new Meeting("meeting1", 1);
        Long meetingId = meetingService.createMeeting(memberService.findOne(memberId1), meeting.getName());

        // == member1이 member2를 회의에 초대
        Member mem2 = memberService.findOne(memberId2);
        Optional<Member> byEmail = memberRepository.findByEmail(mem2.getEmail());
        Long meetingId2 = 0L;
        if (byEmail.isPresent()) {
            meetingId2 = meetingService.inviteMember(byEmail.get(), meetingId);
        }

        //when
        // == 회의 멤버 출력 ==
        //Meeting fetchMeeting = meetingService.findFetchMeeting(meetingId);



        List<JoinMeeting> joinMeetings = meetingService.findOne(meetingId).getJoinMeetings();

        //then
        Assertions.assertThat(joinMeetings.get(0).getMember().getEmail()).isEqualTo("111");
        Assertions.assertThat(joinMeetings.get(1).getMember().getEmail()).isEqualTo("999");

//        Assertions.assertThat(fetchMeeting.getJoinMeetings().get(0).getMember().getEmail()).isEqualTo("111");
//        Assertions.assertThat(fetchMeeting.getJoinMeetings().get(1).getMember().getEmail()).isEqualTo("999");

    }

    @Test
    @WithMockUser("testUser")
    public void 중복초대방지() throws Exception {
        //given
        // == 멤버 생성 ==
        MemberInfoDto infoDto1 = new MemberInfoDto("111", "111", "testUSer1");
        Long memberId1 = memberService.save(infoDto1);

        MemberInfoDto infoDto2 = new MemberInfoDto("999", "999", "testUSer2");
        Long memberId2 = memberService.save(infoDto2);


        // == 미팅 생성 ==
        Meeting meeting = new Meeting("meeting1", 1);
        Long meetingId = meetingService.createMeeting(memberService.findOne(memberId1), meeting.getName());

        //when
        // == member1이 member2를 회의에 초대
        Member mem2 = memberService.findOne(memberId2);
        Optional<Member> byEmail = memberRepository.findByEmail(mem2.getEmail());
        Long meetingId2 = 0L;
        if (byEmail.isPresent()) {
            meetingId2 = meetingService.inviteMember(byEmail.get(), meetingId);
        }

        if (byEmail.isPresent()) {
            meetingId2 = meetingService.inviteMember(byEmail.get(), meetingId);
        }

        //then
        List<JoinMeeting> byMeetingId = joinMeetingService.findByIds(meetingId, byEmail.get().getId());

        Assertions.assertThat(byMeetingId.size()).isEqualTo(2);
    }

}