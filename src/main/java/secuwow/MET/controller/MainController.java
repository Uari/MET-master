package secuwow.MET.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import secuwow.MET.domain.LoginInfo;
import secuwow.MET.domain.UserInfo;
import secuwow.MET.dto.LoginInfoDto;
import secuwow.MET.service.LoginInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    Map<String, Object> data;
    private final LoginInfoService loginInfoService;

    @GetMapping("/main")
    public String loginCheck(@NotNull HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(true);
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LOGININFO");

        UserInfo userInfo = loginInfo.getUserInfo();

        if(loginInfoService.tryLogin(loginInfo.getLoginId(), loginInfo.getLoginPw()) == 0) {
            model.addAttribute("userName", session.getAttribute("USERNAME"));
            if(userInfo.getUserGrant() == 0) //관리자
                return "adminMain";
            else
                return "main";
        }else { //세션이 만료되거나 정상적인 접근이 아님
            return "/login";
        }
    }
}
