package egovframework.kit.linc.security;

import egovframework.kit.linc.domain.member.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class RoleMember extends User {

    public Member member;
    public String name;

    public RoleMember(Member member) {
        super(member.getLoginId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));

        this.member = member;
        this.name = member.getName();
    }

    public RoleMember(User user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

}
