package secuwow.MET.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class UserInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long             userId;

    @Column(name = "user_name", nullable = false)
    private String          userName;

    @Column(name = "user_addr", nullable = false)
    private String          userAddr;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "team_id")
    private TeamInfo teamInfo;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "login_index")
    private LoginInfo loginInfo;

    @OneToOne
    @JoinColumn(name = "smtp_index")
    private SmtpInfo smtpInfo;

    @OneToMany(mappedBy = "userInfo")
    @JsonManagedReference
    private List<TrainingInfo> trainingInfo = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    @JsonManagedReference
    private List<TrainingUserInfo> trainingUserInfo = new ArrayList<>();

    @Column(name = "create_user", nullable = false)
    private String          createUser;

    @CreatedDate
    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime   createDateTime;

    @Column(name = "user_grant", nullable = false)
    private int             userGrant;

    @LastModifiedDate
    @Column(name = "last_update_time", nullable = false)
    private LocalDateTime   last_update_time;
}
