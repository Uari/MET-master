package secuwow.MET.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import secuwow.MET.domain.LoginInfo;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LoginInfoRepository{

    private final EntityManager em;

    public void save(LoginInfo loginInfo) {
        if(loginInfo.getLoginIndex() == null) {
            em.persist(loginInfo);
        }else {
            em.merge(loginInfo);
        }
    }

    public LoginInfo findOne(Long loginIndex) {
        return em.find(LoginInfo.class, loginIndex);
    }

    public List<LoginInfo> getLoginInfoList() {
        return em.createQuery("select l from LoginInfo l", LoginInfo.class)
                .getResultList();
    }

    public LoginInfo getLoginInfoByLoginId(String loginId) {
        try {
            return em.createQuery("select l from LoginInfo l where l.loginId = :loginId", LoginInfo.class)
                    .setParameter("loginId", loginId)
                    .getSingleResult();
        }catch (Exception e) {
            return null;
        }
    }
}
