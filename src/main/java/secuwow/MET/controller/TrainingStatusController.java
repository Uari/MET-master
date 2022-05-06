package secuwow.MET.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import secuwow.MET.domain.LoginInfo;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.dto.LoginInfoDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TrainingStatusController {

    @GetMapping("/trainingStatus") // 훈련 상태 조회
    public String trainingStatus(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LOGININFO");
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
        return "/trainingStatus";
    }
}
