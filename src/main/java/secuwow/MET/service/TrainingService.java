package secuwow.MET.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secuwow.MET.domain.LoginInfo;
import secuwow.MET.domain.TrainingInfo;
import secuwow.MET.domain.TrainingUserInfo;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.dto.TrainingDto;
import secuwow.MET.repository.TrainingInfoRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingInfoRepository trainingInfoRepository;

    @Transactional
    public void save(TrainingInfo trainingInfo) {
        trainingInfoRepository.save(trainingInfo);
    }

    @Transactional
    public int addTraining(TrainingDto trainingDto, UserInfo userInfo) {
        try {
            TrainingInfo trainingInfo = new TrainingInfo();

            trainingInfo.setTrainingName(trainingDto.getTrainingName());
            trainingInfo.setCreateDateTime(LocalDateTime.now());
            trainingInfo.setStartTime(trainingDto.getStartTime());
            trainingInfo.setEndTime(trainingDto.getEndTime());
            trainingInfo.setUserInfo(userInfo);

            save(trainingInfo);
            return 0;
        }catch (Exception e) {
            e.printStackTrace();
            return 1; //예상치 못한 에러
        }
    }
}
