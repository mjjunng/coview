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

    private int cnt;

    @OneToMany(mappedBy = "meeting")
    private List<JoinMeeting> joinMeetings = new ArrayList<>();

    public Meeting() {
    }

    public Meeting(String name, int count) {
        this.name = name;
        this.cnt = count;
    }

    public void setHostId(Long hostId){
        this.hostId = hostId;
    }

    /**
     * 회의 참여 인원 수 증가
     */
    public void increaseCount(int cnt){
        this.cnt += cnt;
    }

}
