package coview.coview.service;

import coview.coview.controller.RegisterForm;
import coview.coview.domain.*;
import coview.coview.repository.MemberRepository;
import coview.coview.repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberRepositoryImpl memberRepositoryImpl;
    private final MeetingService meetingService;

    /**
     * 회원 가입
     */
//    @Transactional
//    public Long join(Member member){
//        validateDuplicateMember(member);
//        memberRepository.save(member);
//        return member.getId();
//    }
//
//    private void validateDuplicateMember(Member member) {
//        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
//        if (!findMembers.isEmpty()){
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }

    /**
     * 회원 조회
     */
    public Member findOne(Long memberId){
        return memberRepositoryImpl.findOne(memberId);
    }
//
//    public Member findByEmailAndPassword(String email, String pwd){
//        return memberRepository.findByEmailAndPassword(email, pwd);
//    }
//
//    public Member findByEmail(String email){
//        return memberRepository.findByEmail(email);
//    }
//


    /**
     * 가입된 회원인지 판별
     * return true : 가입한 회원이 없을 때 -> 이 이메일과 아이디로 회원가입 가능
     * return false : 가입한 회원이 있을 때 -> 이 이메일과 아이디로 회원가입 불가능
     */
//    @Transactional
//    public boolean validateJoinedMember(String email, String password){
//        Member member = findByEmailAndPassword(email, password);
//        if (member == null){
//            return true;
//        }else{
//            return false;
//        }
//
//    }


    /**
     * 멤버 조회 -
     */
//    public List<Member> findTotalMember(Long joinMeetingId){
//        return memberRepository.findTotalMember(joinMeetingId);
//    }

    /**
     * 회원의 로그인 상태 파악
     */
//    public Auth findMemberAuth(Long memberId){
//        return memberRepository.findMemberAuth(memberId);
//    }

    /**
     * 회원의 auth 상태 바꾸기
     */
//    public void changeAuth(Long memberId){
//        Member member = findOne(memberId);
//        member.changeAuth();
//    }


    @Override
    public Member loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> memberWrapper = memberRepository.findByEmail(email);
        Member member = memberWrapper.get();

        if (member == null){
            throw new UsernameNotFoundException(email);
        }
        return member;
    }

    @Transactional
    public Long save(MemberInfoDto infoDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        Member member = new Member(infoDto.getEmail(), infoDto.getPassword(),
                infoDto.getName(), MemberStatus.MEMBER, "ROLE_USER");
        return memberRepository.save(member).getId();
    }
}
