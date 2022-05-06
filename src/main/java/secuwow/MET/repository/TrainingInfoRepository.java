package secuwow.MET.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import secuwow.MET.domain.TrainingInfo;
import secuwow.MET.domain.TrainingUserInfo;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class TrainingInfoRepository {
    private final EntityManager em;

    public void save(TrainingInfo trainingInfo) {
        if(trainingInfo.getTrainingId() == null) {
            em.persist(trainingInfo);
        }else {
            em.merge(trainingInfo);
        }
    }

    public TrainingInfo findOne(Long trainingId, Long userId, Long scenarioId) {
        TrainingInfo trainingInfo = em.createQuery("select t from TrainingUserInfo t", TrainingInfo.class).getSingleResult();

        return trainingInfo;
    }

    public TrainingUserInfo findOne(String encryptionCode) {
        TrainingUserInfo trainingUserInfo = em.createQuery("select t from TrainingUserInfo t where t.encryptionCode = :encryptionCode", TrainingUserInfo.class)
                .setParameter("encryptionCode", encryptionCode)
                .getSingleResult();

        return trainingUserInfo;
    }
}
