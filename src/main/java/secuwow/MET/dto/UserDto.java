package secuwow.MET.dto;

import lombok.Getter;
import lombok.Setter;
import secuwow.MET.domain.UserInfo;

import java.time.LocalDateTime;

@Getter @Setter
public class UserDto {
    private Long            userId;
    private String          userName;
    private String          userAddr;
    private String          createUser;
    private LocalDateTime   createDateTime;
    private int             userGrant;
    private LocalDateTime   last_update_time;

    public UserDto() {
        userId = null;
    }

    public UserDto(UserInfo userInfo) {
        userId      = userInfo.getUserId();
        userName    = userInfo.getUserName();
        userAddr    = userInfo.getUserAddr();
        createUser  = userInfo.getCreateUser();
        createDateTime = userInfo.getCreateDateTime();
        userGrant   = userInfo.getUserGrant();
        last_update_time = userInfo.getLast_update_time();
    }
}
