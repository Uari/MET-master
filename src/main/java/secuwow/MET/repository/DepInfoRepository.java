package secuwow.MET.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import secuwow.MET.domain.DepInfo;
import secuwow.MET.domain.LoginInfo;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepInfoRepository {
    private final EntityManager em;

    public void save(DepInfo depInfo) {
        if(depInfo.getDepId() == null) {
            em.persist(depInfo);
        }else {
            em.merge(depInfo);
        }
    }

    public DepInfo findOne(Long depId) {
        return em.find(DepInfo.class, depId);
    }

    public List<DepInfo> getDepInfoListByCorpId(Long corpId) {
        return em.createQuery("select d from DepInfo d where d.corpInfo.corpId = :corpId", DepInfo.class)
                .setParameter("corpId", corpId)
                .getResultList();
    }
}
