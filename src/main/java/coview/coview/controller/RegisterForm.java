package coview.coview.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class RegisterForm {
    @NotEmpty(message = "아이디는 필수 입니다")
    private String email;
    @NotEmpty(message = "비밀번호는 필수 입니다")
    private String password;
    @NotEmpty(message = "비밀번호를 다시 입력하세요")
    private String checkPassword;
    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String member_name;


    public RegisterForm() {
    }

    public RegisterForm(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.member_name = name;
    }
}
