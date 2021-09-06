package coview.coview.service;

import coview.coview.controller.RegisterForm;
import coview.coview.domain.*;
import coview.coview.repository.MeetingRepository;
import coview.coview.repository.MemberRepository;
import coview.coview.repository.MemberRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired
    MemberRepositoryImpl memberRepositoryImpl;
    @Autowired JoinMeetingService joinMeetingService;

    @Test
    public void 회원가입() throws Exception{
        //given
//        Member member = new Member("123","123", "123", MemberStatus.MEMBER , Auth.LOGOUT);
//
//        //when
        Long findMemberId = memberService.save(new MemberInfoDto("123", "123",
                "kim"));
//        //then
        Member one = memberRepositoryImpl.findOne(findMemberId);
        Assertions.assertThat(findMemberId).isEqualTo(1L);
        Assertions.assertThat(one.getEmail()).isEqualTo("123");

    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원() throws Exception {
        //given
//        Member member1 = new Member("123", "123", "kim", MemberStatus.MEMBER, Auth.LOGOUT);
//        Member member2 = new Member("123", "111", "han", MemberStatus.HOST, Auth.LOGOUT);
//
//        //when
//        memberService.join(member1);
//        memberService.join(member2); // 예외 발생해야 함
//
//        //then
//        fail("회원 중복 예외가 발생해야 한다!");
    }

    @Test
    public void 미팅생성() throws Exception {
        //given
//        Member member = new Member("123", "123", "kim", MemberStatus.MEMBER, Auth.LOGOUT);
//        Meeting meeting = new Meeting();
//
//        memberService.join(member);
//
//        Assertions.assertThat(member.getId()).isEqualTo(1L);
//
//        //when
//        Long meetingId = joinMeetingService.createMeeting(member.getId(), "test1");
//
//        //then
//        Assertions.assertThat(meetingId).isEqualTo(joinMeetingService.findOne(meetingId).getId());
    }

    @Test
    @Rollback(value = false)
    public void 회의리스트() throws Exception {
        //given
//        Member member1 = new Member("123", "123", "kim", MemberStatus.MEMBER, Auth.LOGOUT);
//        Member member2 = new Member("1234", "123", "lee", MemberStatus.MEMBER, Auth.LOGOUT);
//        Meeting meeting1 = new Meeting();
//        Meeting meeting2 = new Meeting();
//        Meeting meeting3 = new Meeting();
//
//        memberService.join(member1);
//        memberService.join(member2);
//
//        //when
//        joinMeetingService.createMeeting(member1.getId(), "test1");
//        joinMeetingService.createMeeting(member1.getId(), "test2");
//        joinMeetingService.createMeeting(member1.getId(), "test3");
//
//        joinMeetingService.createMeeting(member2.getId(), "test3");
//        joinMeetingService.createMeeting(member2.getId(), "test1");
//
//        //then
//        Assertions.assertThat(member1.getMeetings().size()).isEqualTo(3);
//        Assertions.assertThat(member1.getStatus()).isEqualTo(MemberStatus.HOST);
//
//        Assertions.assertThat(member2.getMeetings().size()).isEqualTo(2);
//        Assertions.assertThat(member2.getStatus()).isEqualTo(MemberStatus.HOST);

    }

    @Test
    @Rollback(value = false)
    public void 가입유뮤테스트(){
//        Member member1 = new Member("123", "123", "kim", MemberStatus.MEMBER, Auth.LOGOUT);
//
//        memberService.join(member1);
//
//        //가입한 회원
//        Member findMember = memberService.findByEmailAndPassword(member1.getEmail(), member1.getPassword());
//        Assertions.assertThat(member1).isEqualTo(findMember);
//        Boolean check = memberService.validateJoinedMember(member1.getEmail(), member1.getPassword());
//        Assertions.assertThat(check).isEqualTo(false);
//
//        //미가입 회원
//        Member findMember2 = memberService.findByEmailAndPassword("test1","000");
//        Assertions.assertThat(findMember2).isEqualTo(null);
//        boolean check2 = memberService.validateJoinedMember("test1", "asd");
//        Assertions.assertThat(check2).isEqualTo(true);

    }

    @Test
    public void 회원가입상태확인() throws Exception {
        //given
//        Member member = new Member("123","123", "123", MemberStatus.MEMBER , Auth.LOGOUT);
//
//
//        Assertions.assertThat(member.getAuth()).isEqualTo(Auth.LOGOUT);
//        //when
//        Long findMemberId = memberService.join(member);
//        // Auth : 로그인으로 변경
//        memberService.changeAuth(findMemberId);
//
//        //then
//        Assertions.assertThat(member.getAuth()).isEqualTo(Auth.LOGIN);
    }

}