package secuwow.MET.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secuwow.MET.domain.CorpInfo;
import secuwow.MET.domain.ScenarioInfo;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.dto.UserDto;
import secuwow.MET.repository.UserInfoRepository;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Transactional
    public void save(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

    public List<UserInfo> findAllUserInfo() {
        return userInfoRepository.findAll();
    }

    public List<UserInfo> getUserInfoListByTeamId(Long teamId) {
        return userInfoRepository.getUserInfoListByTeamId(teamId);
    }

    public UserDto findOneToDto(Long userId) {
        UserInfo userInfo = userInfoRepository.findOne(userId);
        UserDto userDto = new UserDto(userInfo);

        return userDto;
    }

    public UserInfo findOneToInfo(Long userId) {
        UserInfo userInfo = userInfoRepository.findOne(userId);

        return userInfo;
    }
}
