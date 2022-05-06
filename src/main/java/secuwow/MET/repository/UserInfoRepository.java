package secuwow.MET.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import secuwow.MET.domain.UserInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserInfoRepository {

    private final EntityManager em;

    public void save(UserInfo userInfo) {
        if(userInfo.getUserId() == null) {
            em.persist(userInfo);
        }else {
            em.merge(userInfo);
        }
    }

    public UserInfo findOne(Long userId ) {
        return em.find(UserInfo.class, userId);
    }

    public List<UserInfo> findAll() {
        return em.createQuery("select u from UserInfo u", UserInfo.class).getResultList();
    }

    public  List<UserInfo> getUserInfoListByTeamId(Long teamId) {
        return em.createQuery("select u from UserInfo u where u.teamInfo.teamId = :teamId", UserInfo.class)
                .setParameter("teamId", teamId)
                .getResultList();
    }

}
