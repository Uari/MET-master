package secuwow.MET.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secuwow.MET.domain.CorpInfo;
import secuwow.MET.domain.DepInfo;
import secuwow.MET.domain.TeamInfo;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.repository.CorpInfoRepository;
import secuwow.MET.repository.DepInfoRepository;
import secuwow.MET.repository.TeamInfoRepository;
import secuwow.MET.repository.UserInfoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrganizationService {

    private final CorpInfoRepository corpInfoRepository;

    private final DepInfoRepository depInfoRepository;

    private final TeamInfoRepository teamInfoRepository;

    private final UserInfoRepository userInfoRepository;

    @Transactional
    public void saveCorp(CorpInfo corpInfo) {
        corpInfoRepository.save(corpInfo);
    }

    @Transactional
    public void saveDep(DepInfo depInfo) {
        depInfoRepository.save(depInfo);
    }

    @Transactional
    public void saveTeam(TeamInfo teamInfo) {
        teamInfoRepository.save(teamInfo);
    }

    public List<CorpInfo> getCorpInfoList(Long userCorpId, int userGrant) {
        List<CorpInfo> corpInfoList = new ArrayList<>();

        if(userGrant == 0) { // root 서버관리자
            //corp 다가져오기
            corpInfoList = corpInfoRepository.getCorpInfoList();
        }else if(userGrant == 1) { // 회사관리자
            corpInfoList.add(corpInfoRepository.findOne(userCorpId));
        }else {  // 사원 일반계정 은 접근 불가 어케들어왔노
            return null;
        }

        return corpInfoList;
    }

    public List<DepInfo> getDepInfoList(Long selectedCorpId) {
        return depInfoRepository.getDepInfoListByCorpId(selectedCorpId);
    }

    public List<TeamInfo> getTeamInfoList(Long selectedDepId) {
        return teamInfoRepository.getTeamInfoListByDepId(selectedDepId);
    }

    public List<UserInfo> getUserInfoList(Long selectedTeamId) {
        return userInfoRepository.getUserInfoListByTeamId(selectedTeamId);
    }

    public Map<String, List> getAllByCorpId(Long selectedCorpId) {
        Map<String, List> result = new HashMap<>();
        List<DepInfo> depInfoList;
        List<TeamInfo> teamInfoList = new ArrayList<>();
        List<UserInfo> userInfoList = new ArrayList<>();

        depInfoList = getDepInfoList(selectedCorpId);

        for (DepInfo depInfo : depInfoList) {
            teamInfoList.addAll(getTeamInfoList(depInfo.getDepId()));
        }

        for(TeamInfo teamInfo : teamInfoList) {
            userInfoList.addAll(getUserInfoList(teamInfo.getTeamId()));
        }

        result.put("DEP", depInfoList);
        result.put("TEAM", teamInfoList);
        result.put("USER", userInfoList);

        // corpId에 포함된 정보를 다 가지고 와서(DEP, TEAM, USER) Map으로 넘겨주기
        return result;
    }

    public Map<String, List> getAllByDepId(Long selectedDepId) {
        Map<String, List> result = new HashMap<>();
        List<TeamInfo> teamInfoList;
        List<UserInfo> userInfoList = new ArrayList<>();

        teamInfoList = getTeamInfoList(selectedDepId);

        for(TeamInfo teamInfo : teamInfoList) {
            userInfoList.addAll(getUserInfoList(teamInfo.getTeamId()));
        }
        result.put("TEAM", teamInfoList);
        result.put("USER", userInfoList);

        // depId에 포함된 정보를 다 가지고 와서(TEAM, USER) Map으로 넘겨주기
        return result;
    }

    public Map<String, List> getAllByTeamId(Long selectedTeamId) {
        Map<String, List> result = new HashMap<>();
        List<UserInfo> userInfoList;

        userInfoList = getUserInfoList(selectedTeamId);

        result.put("USER", userInfoList);

        // teamId에 포함된 정보를 다 가지고 와서(USER) Map으로 넘겨주기
        return result;
    }
}
