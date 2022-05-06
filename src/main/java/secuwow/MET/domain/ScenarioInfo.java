package secuwow.MET.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ScenarioInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scenario_id")
    private Long    scenarioId;

    @Column(name = "scenario_name", nullable = false)
    private String  scenarioName;

    @Column(name = "send_mail_user", nullable = false)
    private String  sendMailUser;

    @Column(name = "send_mail_addr", nullable = false)
    private String  sendMailAddr;

    @Column(name = "mail_title", nullable = false)
    private String  mailTitle;

    @Column(name = "mail_content", columnDefinition = "LONGTEXT", nullable = false)
    private String  mailContent;

    @Column(name = "attach_file_path")
    private String  filePath;

    @Column(name = "create_date_time", nullable = false)
    private LocalDateTime createDateTime;

    @Column(name = "create_user", nullable = false)
    private String  createUser;

    //숫자로 할지 문자로 할지 아직 안정함 추후 더 맞는 방법 채택
    @Column(name = "scenario_difficulty", nullable = false)
    private String  scenarioDifficulty;

    /* 0 = 해당부분 시나리오에 포함 안됨, 1 = 해당부분 시나리오에 포함됨
    0       0       0       0           0
    메일열람 링크클릭  다운로드 첨부파일실행  개인정보입력
    입력은 10진수로 변환해서 입력
    ex) 10110 = 22
    */
    @Column(name = "scenario_element", nullable = false)
    private int     scenarioElement;

    @OneToMany(mappedBy = "scenarioInfo")
    @JsonManagedReference
    private List<ScenarioInfo> scenarioInfo = new ArrayList<>();

}
