package coview.coview.service;

import coview.coview.domain.JoinMeeting;
import coview.coview.domain.Meeting;
import coview.coview.domain.Member;
import coview.coview.repository.JoinMeetingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class JoinMeetingService {
    private final JoinMeetingRepository joinMeetingRepository;
    private final MeetingService meetingService;
    private final MemberService memberService;

    /**
     *
     * JoinMeeting 저장
     */
    @Transactional
    public Long save(JoinMeeting joinMeeting){
        joinMeetingRepository.save(joinMeeting);
        return joinMeeting.getId();
    }


    /**
     * meeting 생성
     */
    @Transactional
    public Long createMeeting(Long memberId, String name){

        Meeting meeting = new Meeting();
        meetingService.save(meeting);
        Member findMember = memberService.findOne(memberId);
        JoinMeeting joinMeeting = new JoinMeeting(findMember, meeting, name);
        joinMeeting.setHostId(memberId);
        findMember.setMeetings(joinMeeting);
        meeting.setJoinMeetings(joinMeeting);
        findMember.changeStatus();
        save(joinMeeting);
        return joinMeeting.getId();
    }

    /**
     * 조회
     */
    public JoinMeeting findOne(Long id){
        return joinMeetingRepository.findOne(id);
    }


    /**
     * 멤버 초대
     */
    @Transactional
    public Long inviteMember(String memberEmail, Long joinMeetingId){
        JoinMeeting findJoinMeeting = findOne(joinMeetingId);
//        Long meetingId = findJoinMeeting.getMeeting().getId();
        //Meeting findMeeting = meetingService.findOne(meetingId);
        List<Member> invitedMember = memberService.findByEmail(memberEmail);
        invitedMember.get(0).setMeetings(findJoinMeeting);
        //findJoinMeeting.setMembersIds(invitedMember.get(0).getId());
        //findMeeting.setJoinMeetings(findJoinMeeting);
        return joinMeetingId;
    }

}
