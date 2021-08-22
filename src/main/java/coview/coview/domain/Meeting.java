package coview.coview.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
