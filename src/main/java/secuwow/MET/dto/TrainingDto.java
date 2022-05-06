package secuwow.MET.dto;

import lombok.Getter;
import lombok.Setter;
import secuwow.MET.domain.TrainingInfo;

import java.time.LocalDateTime;

@Getter @Setter
public class TrainingDto {

    private Long        trainingId;

    private String      trainingName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createDateTime;

    private Long userId;

    public TrainingDto() {
        trainingId = null;
    }

    public TrainingDto(TrainingInfo trainingInfo) {
        trainingId      = trainingInfo.getTrainingId();
        trainingName    = trainingInfo.getTrainingName();
        startTime       = trainingInfo.getStartTime();
        endTime         = trainingInfo.getEndTime();
        createDateTime  = trainingInfo.getCreateDateTime();
        userId          = trainingInfo.getTrainingId();
    }
}
