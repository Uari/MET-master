package secuwow.MET.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.UniqueElements;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class LoginInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_index")
    private Long     loginIndex;

    @NotEmpty
    @Column(name = "user_login_id", nullable = false, unique = true)
    private String  loginId;

    @NotEmpty
    @Column(name = "user_login_passwd", nullable = false)
    private String  loginPw;

    @ColumnDefault("0")
    @Column(name = "try_count", nullable = false)
    private int     tryCount;

    @NotNull
    @Column(name = "create_user", nullable = false)
    private String  createUser;

    @NotNull
    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @OneToOne(mappedBy = "loginInfo")
    @JsonBackReference
    private UserInfo userInfo;

}
