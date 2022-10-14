package egovframework.kit.linc.security;

import egovframework.kit.linc.domain.member.Member;
import egovframework.kit.linc.domain.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        Member member = memberRepository.findByLoginId(s).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new RoleMember(member);
    }
}
