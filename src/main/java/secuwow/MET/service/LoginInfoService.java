package secuwow.MET.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secuwow.MET.domain.LoginInfo;
import secuwow.MET.repository.LoginInfoRepository;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginInfoService {

    private final LoginInfoRepository loginInfoRepository;

    @Transactional
    public void save(LoginInfo loginInfo) {
        loginInfoRepository.save(loginInfo);
    }

    //0 : 로그인 성공, 1 : 아이디 없음, 2 : 비밀번호가 틀림, 3 : 에러
    @Transactional
    public int tryLogin(String loginId, String loginPw) {
        try {
            LoginInfo loginInfo = loginInfoRepository.getLoginInfoByLoginId(loginId);

            if(loginInfo == null)
                return 1; //일치하는 아이디 존재 하지 않음

            if(loginInfo.getLoginPw().equals(loginPw)) {
                loginInfo.setTryCount(0);
                return 0; //로그인 성공
            }
            loginInfo.setTryCount(loginInfo.getTryCount() + 1);
            return 2; //비밀번호가 틀림
        }catch (Exception e) {
            e.printStackTrace();
            return 3; //예상치 못한 에러
        }
    }

    public LoginInfo getLoginInfoByLoginId(String loginId) {
        return loginInfoRepository.getLoginInfoByLoginId(loginId);
    }
}
