package secuwow.MET.dto;

import lombok.Getter;
import lombok.Setter;
import secuwow.MET.domain.ScenarioInfo;

import java.time.LocalDateTime;

@Getter @Setter
public class ScenarioDto {
    private Long    scenarioId;
    private String  scenarioName;
    private String  sendMailUser;
    private String  sendMailAddr;
    private String  mailTitle;
    private String  mailContent;
    private String  filePath;
    private LocalDateTime createDateTime;
    private String  createUser;
    private String  scenarioDifficulty;
    private int     scenarioElement;

    public ScenarioDto(ScenarioInfo scenarioInfo) {
        scenarioId   = scenarioInfo.getScenarioId();
        scenarioName = scenarioInfo.getScenarioName();
        sendMailUser = scenarioInfo.getSendMailUser();
        sendMailAddr = scenarioInfo.getSendMailAddr();
        mailTitle    = scenarioInfo.getMailTitle();
        mailContent  = scenarioInfo.getMailContent();
        filePath     = scenarioInfo.getFilePath();
        createDateTime = scenarioInfo.getCreateDateTime();
        createUser   = scenarioInfo.getCreateUser();
        createUser   = scenarioInfo.getCreateUser();
        scenarioDifficulty = scenarioInfo.getScenarioDifficulty();
        scenarioElement = scenarioInfo.getScenarioElement();
    }
}
