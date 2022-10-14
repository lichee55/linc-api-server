package egovframework.kit.linc.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {

    private String loginId;
    private String name;
    private String role;
}
