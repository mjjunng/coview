package coview.coview.domain;

import java.io.Serializable;

public class JoinMeetingId implements Serializable {
    private Member member;
    private Meeting meeting;

    public JoinMeetingId() {
    }

    public JoinMeetingId(Member member, Meeting meeting) {
        this.member = member;
        this.meeting = meeting;
    }
}
