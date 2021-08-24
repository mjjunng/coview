package coview.coview.service;

import coview.coview.domain.JoinMeeting;
import coview.coview.domain.JoinMeetingId;
import coview.coview.domain.Meeting;
import coview.coview.domain.Member;
import coview.coview.repository.JoinMeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
        findMember.setMeetings(joinMeeting);
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

}
