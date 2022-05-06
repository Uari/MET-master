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
import secuwow.MET.domain.LoginInfo;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.dto.LoginInfoDto;
import secuwow.MET.dto.TrainingDto;
import secuwow.MET.service.ScenarioService;
import secuwow.MET.service.TrainingService;
import secuwow.MET.service.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @GetMapping("/training") // 훈련 등록
    public String training(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        LoginInfoDto loginInfoDto = new LoginInfoDto(loginInfo);

        if(loginInfoDto.getLoginId().equals(null))
        {
            return "redirect:/login?login=sessionExpires";
        }
        model.addAttribute("userName", session.getAttribute("USERNAME"));

        UserInfo userInfo = loginInfo.getUserInfo();

        List trainingInfoList = userInfo.getTrainingInfo();
        JSONObject json = new JSONObject();
        json.put("trainingInfo", trainingInfoList);
        return "/training";
    }

    @RequestMapping(value = "/addTraining", method = RequestMethod.POST)
    public String teamInfoList(@NotNull HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        LoginInfoDto loginInfoDto = new LoginInfoDto(loginInfo);

        if(loginInfoDto.getLoginId().equals(null))
        {
            return "redirect:/login?login=sessionExpires";
        }
        UserInfo userInfo = loginInfo.getUserInfo();

        String trainingName = request.getParameter("trainingName");
        String startTime    = request.getParameter("trainingStartTime");
        String endTime      = request.getParameter("trainingEndTime");


        TrainingDto trainingDto = new TrainingDto();

        trainingDto.setTrainingName(trainingName);
        trainingDto.setStartTime(LocalDateTime.parse(startTime));
        trainingDto.setEndTime(LocalDateTime.parse(endTime));

        trainingService.addTraining(trainingDto, userInfo);

        return "fragments/selectSendUserModal :: #selectDep";
    }

}
