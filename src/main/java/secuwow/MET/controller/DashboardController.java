package secuwow.MET.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import secuwow.MET.domain.LoginInfo;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.service.LoginInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DashboardController {

    @GetMapping("/dashboard") // 대시보드
    public String dashboard(@NotNull HttpServletRequest request, @NotNull Model model) throws Exception {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LOGININFO");
        model.addAttribute("userName", session.getAttribute("USERNAME"));

        UserInfo userInfo = loginInfo.getUserInfo();

        List trainingInfoList = userInfo.getTrainingInfo();
        JSONObject json = new JSONObject();
        json.put("trainingInfo", trainingInfoList);
        return "/dashboard";
    }
}
