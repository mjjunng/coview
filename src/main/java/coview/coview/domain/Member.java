package coview.coview.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    @Column(name = "member_name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "member")
    private List<JoinMeeting> meetings = new ArrayList<>();

    public Member(){}

    public Member(String email, String password, String name, MemberStatus status) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.status = status;
    }
}
