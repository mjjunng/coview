package coview.coview.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
public class Member implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "member_name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;

    private String auth;

    @OneToMany(mappedBy = "member")
    private List<JoinMeeting> meetings = new ArrayList<>();

    public Member(){}

    @Builder
    public Member(String email, String password, String name, MemberStatus status, String auth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.status = status;
        this.auth = auth;
    }

    //==비지니스 로직==
    /**
     * 회원 상태 호스트로 바꾸기
     */
    public void changeStatus(){
        this.status = MemberStatus.HOST;
    }


    /**
     * 로그인 상태 바꾸기
     */
//    public void changeAuth(){
//        if (this.getAuth() == Auth.LOGIN){
//            this.auth = Auth.LOGOUT;
//        }
//        else if (this.getAuth() == Auth.LOGOUT){
//            this.auth = Auth.LOGIN;
//        }
//    }


    /**
     * ==연관관계 메서드
     */
    public void setMeetings(JoinMeeting joinMeeting){
        getMeetings().add(joinMeeting);

    }

    /**
     *
     * spring security
     */
    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        auths.add(new SimpleGrantedAuthority(auth));
        return auths;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
