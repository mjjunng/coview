package coview.coview.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class JoinMeeting {

    @Id @GeneratedValue
    @Column(name = "join_meeting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Column(name = "meeting_name")
    private String name;

    public JoinMeeting (){}

    public JoinMeeting(Member member, Meeting meeting, String name) {
        this.member = member;
        this.meeting = meeting;
        this.name = name;
    }

    //--연관관계 편의 메서드--//
    public void setMember(Member member){
        this.member = member;
        member.getMeetings().add(this);
    }

    public void setMeeting(Meeting meeting){
        this.meeting = meeting;
        meeting.getJoinMeetings().add(this);
    }



}
