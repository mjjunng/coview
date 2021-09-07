package coview.coview.service;

import coview.coview.domain.JoinMeeting;
import coview.coview.domain.Meeting;
import coview.coview.domain.Member;
import coview.coview.repository.JoinMeetingRepository;
import coview.coview.repository.MeetingRepository;
import coview.coview.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final JoinMeetingService joinMeetingService;

    /**
     *
     * 회의 저장
     */
    @Transactional
    public Long save(Meeting meeting){
        meetingRepository.save(meeting);
        return meeting.getId();
    }

    /**
     * 회의 조회
     */
    public Meeting findOne(Long meetingId){
        return meetingRepository.findOne(meetingId);
    }

    public List<Meeting> findAll(){
        return meetingRepository.findAll();
    }

    /**
     * 회의 생성
     */
    @Transactional
    public Long createMeeting(Member member, String name){
        Meeting meeting = new Meeting(name);
        meeting.setHostId(member.getId());
        save(meeting);
        JoinMeeting joinMeeting = new JoinMeeting(member, meeting, name);
        joinMeeting.setMember(member);
        joinMeeting.setMeeting(meeting);
        member.changeStatus();
        joinMeetingService.save(joinMeeting);
        return meeting.getId();
    }

    /**
     * 멤버 초대
     */
    @Transactional
    public Long inviteMember(Member member, Long meetingId){
        Meeting findMeeting = findOne(meetingId);
        JoinMeeting joinMeeting = new JoinMeeting(member, findMeeting, findMeeting.getName());
        joinMeeting.setMember(member);
        joinMeeting.setMeeting(findMeeting);
        joinMeetingService.save(joinMeeting);
        return findMeeting.getId();
    }
}
