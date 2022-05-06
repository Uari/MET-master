package secuwow.MET.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import secuwow.MET.domain.ScenarioInfo;
import secuwow.MET.domain.TrainingUserInfo;
import secuwow.MET.dto.TrainingUserInfoDto;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrainingUserInfoRepository {
    private final EntityManager em;

    public void save(TrainingUserInfo trainingUserInfo) {
        if(trainingUserInfo.getTrainingUserId() == null) {
            em.persist(trainingUserInfo);
        }else {
            em.merge(trainingUserInfo);
        }
    }

    public TrainingUserInfo findOne(Long trainingId, Long userId, Long scenarioId) {
        TrainingUserInfo trainingUserInfo = em.createQuery("select t from TrainingUserInfo t" +
                        " where t.trainingInfo.trainingId = :trainingId" +
                        " and t.userInfo.userId = :userId" +
                        " and t.scenarioInfo.scenarioId = :scenarioId", TrainingUserInfo.class)
                .setParameter("trainingId", trainingId)
                .setParameter("userId", userId)
                .setParameter("scenarioId", scenarioId)
                .getSingleResult();

        return trainingUserInfo;
    }

    public TrainingUserInfo findOne(String encryptionCode) {
        TrainingUserInfo trainingUserInfo = em.createQuery("select t from TrainingUserInfo t where t.encryptionCode = :encryptionCode", TrainingUserInfo.class)
                .setParameter("encryptionCode", encryptionCode)
                .getSingleResult();

        return trainingUserInfo;
    }
}
