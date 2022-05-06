package secuwow.MET.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import secuwow.MET.domain.LoginInfo;
import secuwow.MET.domain.ScenarioInfo;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.dto.LoginInfoDto;
import secuwow.MET.dto.ScenarioDto;
import secuwow.MET.dto.UserDto;
import secuwow.MET.function.InitPage;
import secuwow.MET.function.PostMail;
import secuwow.MET.service.ScenarioService;
import secuwow.MET.service.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SendMailController {

    private final ScenarioService scenarioService;
    private final UserInfoService userInfoService;
    private PostMail postMail = new PostMail();

    @GetMapping("/sendMail")
    public String sendMail(@NotNull HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LOGININFO");
        LoginInfoDto loginInfoDto = new LoginInfoDto(loginInfo);

        if(loginInfoDto.getLoginId().equals(null))
        {
            return "redirect:/login?login=sessionExpires";
        }

        model.addAttribute("userName", session.getAttribute("USERNAME"));

        UserInfo userInfo = loginInfo.getUserInfo();
        /*if(userInfo.getUserGrant() != 0) {
            System.out.println("222222222222222222222222222222222222222222222");
            return "main?access=not_available";
        }*/
        List<ScenarioInfo> scenarioInfoList = scenarioService.findAll();


        model.addAttribute("scenarioInfoList", scenarioInfoList);



        return "/sendMail";
    }

    @RequestMapping(value = "/scenarioInfoLoad", method = RequestMethod.POST)
    public String userInfoList(@NotNull HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LOGININFO");

        UserInfo userInfo = loginInfo.getUserInfo();

        Long selectedTeamId = Long.parseLong(request.getParameter("scenarioId")); //scenarioId

        //관리자 외 접근 금지 추가

        //TEAM, USER 키워드로 리스트를 받아옴
        ScenarioDto scenarioDto = scenarioService.findScenarioInfoByScenarioId(selectedTeamId);

        model.addAttribute("scenarioInfo", scenarioDto);

        return "sendMail :: #scenarioInfoForm";
    }

    @RequestMapping(value = "/postMail", method = RequestMethod.POST)
    public String postMail(@NotNull HttpServletRequest request, Model model) throws Exception {
        InitPage initPage = new InitPage();
        ScenarioDto scenarioDto;
        List<UserDto> userDtoList = new ArrayList<>();

        initPage.servAddr(request);
        Long scenarioId = Long.parseLong(request.getParameter("scenarioId"));
        scenarioDto = scenarioService.findScenarioInfoByScenarioId(scenarioId);

        String[] userIdList = request.getParameterValues("sendUserIds");

        for(String userIdStr : userIdList)
        {
            Long userId = Long.parseLong(userIdStr);
            UserDto userDto = userInfoService.findOneToDto(userId);

            postMail.postMail("test", scenarioDto, userDto);
        }
        return "sendMail";
    }
}
