package secuwow.MET.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class SmtpInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smtp_index")
    private Long     smtpIndex;

    @Column(name = "smtp_host", nullable = false)
    private String  smtpHost;

    @Column(name = "smtp_id", nullable = false)
    private String  smtpId;

    @Column(name = "smtp_pw", nullable = false)
    private String  smtpPw;

    @Column(name = "smtp_port", nullable = false)
    private String  smtpPort;

    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @OneToOne(mappedBy = "smtpInfo")
    private UserInfo userInfo;

}
