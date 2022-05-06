package secuwow.MET.dto;

import lombok.Getter;
import lombok.Setter;
import secuwow.MET.domain.LoginInfo;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoginInfoDto {
    private String  loginId;
    private String  loginPw;
    private LocalDateTime createDateTime;

    public LoginInfoDto(LoginInfo loginInfo) {
        loginId = loginInfo.getLoginId();
        loginPw = loginInfo.getLoginPw();
        createDateTime = loginInfo.getCreateDateTime();
    }
}
