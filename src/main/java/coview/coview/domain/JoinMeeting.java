package coview.coview.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@IdClass(JoinMeetingId.class)
public class JoinMeeting {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
}
