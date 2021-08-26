package coview.coview.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Meeting {

    @Id @GeneratedValue
    @Column(name = "meeting_id")
    private Long id;

//    @Column(name = "meeting_name")
//    private String name;
//
//    public Meeting(String name) {
//        this.name = name;
//    }

    @OneToMany(mappedBy = "meeting")
    private List<JoinMeeting> joinMeetings = new ArrayList<>();

    /**
     * ==연관관계 메서드
     */
    public void setJoinMeetings(JoinMeeting joinMeeting){
        getJoinMeetings().add(joinMeeting);
    }
}
