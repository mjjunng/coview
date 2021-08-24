package coview.coview.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginForm {

//    @NotEmpty(message = "아이디는 필수 입니다")
//    private String email;
//    @NotEmpty(message = "비밀번호는 필수 입니다")
//    private String password;
//    @NotEmpty(message = "비밀번호를 다시 입력하세요")
//    private String checkPassword;
//    @NotEmpty(message = "회원 이름은 필수 입니다")
//    private String member_name;
    @NotEmpty(message = "아이디를 입력하세요")
    private String email;
    @NotEmpty(message = "비밀번호를 입력하세요")
    private String password;

}
