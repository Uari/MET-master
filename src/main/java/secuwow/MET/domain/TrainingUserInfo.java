package secuwow.MET.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class TrainingUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_user_id")
    private Long    trainingUserId;

    @Column(name = "display_flag", nullable = false)
    private int     flagDisplay;

    @Column(name = "display_time", nullable = false)
    private LocalDateTime displayTime;

    @Column(name = "click_flag", nullable = false)
    private int     flagClick;

    @Column(name = "download_flag", nullable = false)
    private int     flagDownload;

    @Column(name = "run_flag", nullable = false)
    private int     flagRun;

    @Column(name = "insert_private_info_flag", nullable = false)
    private int     flagPrivateInfoInsert;

    @Column(name = "send_time", nullable = false)
    private LocalDateTime sendTime;

    @Column(name = "encryption_code", nullable = false, unique = true)
    private String encryptionCode;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo    userInfo;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "training_id", nullable = false)
    private TrainingInfo    trainingInfo;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "scenario_id", nullable = false)
    private ScenarioInfo    scenarioInfo;

}
