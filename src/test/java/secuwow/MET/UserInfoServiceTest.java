package secuwow.MET;

import lombok.RequiredArgsConstructor;
import org.hibernate.type.LocalDateType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.repository.UserInfoRepository;
import secuwow.MET.service.UserInfoService;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserInfoServiceTest {

    @Autowired UserInfoService userInfoService;
    @Autowired UserInfoRepository userInfoRepository;
    @Autowired EntityManager em;

    @Test
    public void 테스트() throws Exception {
        //given


        //when

        //then
    }
}
