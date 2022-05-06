package secuwow.MET.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import secuwow.MET.domain.ScenarioInfo;
import secuwow.MET.domain.TrainingInfo;
import secuwow.MET.domain.TrainingUserInfo;
import secuwow.MET.domain.UserInfo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
public class TrainingUserInfoDto {

    private Long    trainingUserId;

    private int     flagDisplay;

    private LocalDateTime displayTime;

    private int     flagClick;

    private int     flagDownload;

    private int     flagRun;

    private int     flagPrivateInfoInsert;

    private LocalDateTime sendTime;

    private String  encryptionCode;

    private Long    userId;

    private Long    trainingId;

    private Long    scenarioId;

    public TrainingUserInfoDto(TrainingUserInfo trainingUserInfo) {
        trainingUserId  = trainingUserInfo.getTrainingUserId();
        flagDisplay     = trainingUserInfo.getFlagDisplay();
        displayTime     = trainingUserInfo.getDisplayTime();
        flagClick       = trainingUserInfo.getFlagClick();
        flagDownload    = trainingUserInfo.getFlagDownload();
        flagRun         = trainingUserInfo.getFlagRun();
        flagPrivateInfoInsert = trainingUserInfo.getFlagPrivateInfoInsert();
        sendTime        = trainingUserInfo.getSendTime();
        encryptionCode  = trainingUserInfo.getEncryptionCode();
        userId          = trainingUserInfo.getUserInfo().getUserId();
        trainingId      = trainingUserInfo.getTrainingInfo().getTrainingId();
        scenarioId      = trainingUserInfo.getScenarioInfo().getScenarioId();
    }
}
