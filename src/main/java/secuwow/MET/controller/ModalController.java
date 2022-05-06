package secuwow.MET.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import secuwow.MET.domain.*;
import secuwow.MET.dto.LoginInfoDto;
import secuwow.MET.service.OrganizationService;
import secuwow.MET.service.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ModalController {
    private final OrganizationService organizationService;

    @RequestMapping(value = "/callCorpInfo", method = RequestMethod.POST)
    public String corpInfoList(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LOGININFO");
        LoginInfoDto loginInfoDto = new LoginInfoDto(loginInfo);

        if(loginInfoDto.getLoginId().equals(null))
        {
            return "redirect:/login?login=sessionExpires";
        }

        UserInfo userInfo = loginInfo.getUserInfo();

        List<CorpInfo> corpInfoList = organizationService.getCorpInfoList(userInfo.getTeamInfo().getDepInfo().getCorpInfo().getCorpId(), userInfo.getUserGrant());

        model.addAttribute("corpInfoList", corpInfoList);

        return "fragments/selectSendUserModal :: #corpNames";
    }

    @RequestMapping(value = "/callDepInfo", method = RequestMethod.POST)
    public String depInfoList(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LOGININFO");
        LoginInfoDto loginInfoDto = new LoginInfoDto(loginInfo);

        if(loginInfoDto.getLoginId().equals(null))
        {
            return "redirect:/login?login=sessionExpires";
        }

        UserInfo userInfo = loginInfo.getUserInfo();

        Long selectedCorpId = Long.parseLong(request.getParameter("select")); //corpId

        //관리자 외 접근 금지 추가
        //DEP, TEAM, USER 키워드로 리스트를 받아옴
        Map<String, List> listMap = organizationService.getAllByCorpId(selectedCorpId);

        model.addAttribute("depInfoList", listMap.get("DEP"));
        model.addAttribute("teamInfoList", listMap.get("TEAM"));
        model.addAttribute("userInfoList", listMap.get("USER"));
        return "fragments/selectSendUserModal :: #selectCorp";
    }

    @RequestMapping(value = "/callTeamInfo", method = RequestMethod.POST)
    public String teamInfoList(@NotNull HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LOGININFO");
        LoginInfoDto loginInfoDto = new LoginInfoDto(loginInfo);

        if(loginInfoDto.getLoginId().equals(null))
        {
            return "redirect:/login?login=sessionExpires";
        }

        UserInfo userInfo = loginInfo.getUserInfo();

        Long selectedDepId = Long.parseLong(request.getParameter("select")); //depId

        //관리자 외 접근 금지 추가

        //TEAM, USER 키워드로 리스트를 받아옴
        Map<String, List> listMap = organizationService.getAllByDepId(selectedDepId);

        model.addAttribute("teamInfoList", listMap.get("TEAM"));
        model.addAttribute("userInfoList", listMap.get("USER"));

        return "fragments/selectSendUserModal :: #selectDep";
    }

    @RequestMapping(value = "/callUserInfo", method = RequestMethod.POST)
    public String userInfoList(@NotNull HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LOGININFO");
        LoginInfoDto loginInfoDto = new LoginInfoDto(loginInfo);

        if(loginInfoDto.getLoginId().equals(null))
        {
            return "redirect:/login?login=sessionExpires";
        }

        UserInfo userInfo = loginInfo.getUserInfo();

        Long selectedTeamId = Long.parseLong(request.getParameter("select")); //teamId

        //관리자 외 접근 금지 추가

        //TEAM, USER 키워드로 리스트를 받아옴
        Map<String, List> listMap = organizationService.getAllByTeamId(selectedTeamId);

        model.addAttribute("userInfoList", listMap.get("USER"));

        return "fragments/selectSendUserModal :: #userNames";
    }
}
