package secuwow.MET.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secuwow.MET.domain.TrainingUserInfo;
import secuwow.MET.repository.TrainingUserInfoRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrainingUserInfoService {

    private final TrainingUserInfoRepository trainingUserInfoRepository;

    @Transactional
    public void save(TrainingUserInfo trainingUserInfo) {
        trainingUserInfoRepository.save(trainingUserInfo);
    }

    @Transactional
    public void insertInteractionDisplay(String EncryptionCode) {
        TrainingUserInfo trainingUserInfo = trainingUserInfoRepository.findOne(EncryptionCode);

        trainingUserInfo.setFlagDisplay(1);
        trainingUserInfo.setDisplayTime(LocalDateTime.now());
    }

    @Transactional
    public void insertInteractionClick(String EncryptionCode) {
        TrainingUserInfo trainingUserInfo = trainingUserInfoRepository.findOne(EncryptionCode);

        trainingUserInfo.setFlagClick(1);
    }
}
