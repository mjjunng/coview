package coview.coview.domain;

import lombok.Getter;

import javax.persistence.*;

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

    private Long hostId;

    public JoinMeeting (){}

    public JoinMeeting(Member member, Meeting meeting, String name) {
        this.member = member;
        this.meeting = meeting;
        this.name = name;
    }

    public void setHostId(Long hostId){
        this.hostId = hostId;
    }


}
