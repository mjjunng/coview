package coview.coview.service;

import coview.coview.domain.JoinMeeting;
import coview.coview.domain.Meeting;
import coview.coview.domain.Member;
import coview.coview.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final MeetingService meetingService;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 조회
     */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    public Member findByEmailAndPassword(String email, String pwd){
        return memberRepository.findByEmailAndPassword(email, pwd);
    }

    /**
     * 멤버 조회 -> 해당 멤버의 미팅 목록 불러오기 위해 join Meeting과 join fetch 함
     */
//    public List<Member> findById(Long memberId){
//        return memberRepository.findById(memberId);
//    }

    /**
     * 가입된 회원인지 판별
     * return true : 가입한 회원이 없을 때 -> 이 이메일과 아이디로 회원가입 가능
     * return false : 가입한 회원이 있을 때 -> 이 이메일과 아이디로 회원가입 불가능
     */
    @Transactional
    public boolean validateJoinedMember(String email, String password){
        Member member = findByEmailAndPassword(email, password);
        if (member == null){
            return true;
        }else{
            return false;
        }

    }



}
