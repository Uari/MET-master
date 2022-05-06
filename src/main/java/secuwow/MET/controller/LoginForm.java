package secuwow.MET.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginForm {

    @NotEmpty(message = "아이디를 입력해 주세요")
    private String id;

    @NotEmpty(message = "비밀번호를 입력해 주세요")
    private String pw;
}
