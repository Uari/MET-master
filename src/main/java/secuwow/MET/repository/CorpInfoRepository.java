package secuwow.MET.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import secuwow.MET.domain.CorpInfo;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CorpInfoRepository {

    private final EntityManager em;

    public void save(CorpInfo corpInfo) {
        if(corpInfo.getCorpId() == null) {
            em.persist(corpInfo);
        }else {
            em.merge(corpInfo);
        }
    }

    public CorpInfo findOne(Long corpId) {
        return em.find(CorpInfo.class, corpId);
    }

    public List<CorpInfo> getCorpInfoList() {
        return em.createQuery("select c from CorpInfo c", CorpInfo.class)
                .getResultList();
    }
}
