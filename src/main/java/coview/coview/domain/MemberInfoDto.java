package coview.coview.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoDto {
    private String email;
    private String password;
    private String name;

    public MemberInfoDto() {
    }

    public MemberInfoDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
