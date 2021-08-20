package coview.coview.service;

import coview.coview.domain.Member;
import coview.coview.domain.MemberStatus;
import coview.coview.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        //when
        Long findMemberId = memberService.join(member);
        //then
        Assertions.assertThat(memberRepository.findOne(findMemberId)).isEqualTo(member);

    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원() throws Exception {
        //given
        Member member1 = new Member("123", "123", "kim", MemberStatus.MEMBER);
        Member member2 = new Member("123", "111", "han", MemberStatus.HOST);

        //when
        memberService.join(member1);
        memberService.join(member2); // 예외 발생해야 함

        //then
        fail("회원 중복 예외가 발생해야 한다!");
    }

}