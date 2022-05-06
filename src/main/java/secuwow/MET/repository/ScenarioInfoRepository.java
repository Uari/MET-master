package secuwow.MET.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import secuwow.MET.domain.LoginInfo;
import secuwow.MET.domain.ScenarioInfo;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScenarioInfoRepository {

    private final EntityManager em;

    public void save(ScenarioInfo scenarioInfo) {
        if(scenarioInfo.getScenarioId() == null) {
            em.persist(scenarioInfo);
        }else {
            em.merge(scenarioInfo);
        }
    }

    public ScenarioInfo findOne(Long scenarioId) {

        return em.find(ScenarioInfo.class, scenarioId);
    }

    public List<ScenarioInfo> getScenarioList() {
        return em.createQuery("select s from ScenarioInfo s", ScenarioInfo.class)
                .getResultList();
    }
}
