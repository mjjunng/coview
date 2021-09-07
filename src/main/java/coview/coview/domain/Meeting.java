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

    @Column(name = "meeting_name")
    private String name;

    private Long hostId;

    @OneToMany(mappedBy = "meeting")
    private List<JoinMeeting> joinMeetings = new ArrayList<>();

    public Meeting() {
    }

    public Meeting(String name) {
        this.name = name;
    }

    public void setHostId(Long hostId){
        this.hostId = hostId;
    }

}
