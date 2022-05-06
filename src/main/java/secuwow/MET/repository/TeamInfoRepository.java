package secuwow.MET.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import secuwow.MET.domain.TeamInfo;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeamInfoRepository {
    private final EntityManager em;

    public void save(TeamInfo teamInfo) {
        if(teamInfo.getTeamId() == null) {
            em.persist(teamInfo);
        }else {
            em.merge(teamInfo);
        }
    }

    public TeamInfo findOne(Long teamId) {
        return em.find(TeamInfo.class, teamId);
    }

    public List<TeamInfo> getTeamInfoListByDepId(Long depId) {
        return em.createQuery("select t from TeamInfo t where t.depInfo.depId = :depId", TeamInfo.class)
                .setParameter("depId", depId)
                .getResultList();
    }
}
