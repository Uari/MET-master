package secuwow.MET.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import secuwow.MET.domain.LoginInfo;
import secuwow.MET.function.InitPage;
import secuwow.MET.service.LoginInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginInfoService loginInfoService;

    @GetMapping("/login")
    public String createForm(Model model, HttpServletRequest request) {
        model.addAttribute("loginForm", new LoginForm());

        HttpSession session = request.getSession(true);
        session.invalidate();

        return "/login";
    }

    @PostMapping("/login")
    public String tryLogin(HttpServletRequest request, @Valid LoginForm form, @NotNull BindingResult result) {
        if(result.hasErrors()) {
            return "/login";
        }

        HttpSession session = request.getSession(true);

        LoginInfo loginInfo = new LoginInfo();

        loginInfo.setLoginId(form.getId());
        loginInfo.setLoginPw(form.getPw());

        //0 : 로그인 성공, 1 : 아이디 없음, 2 : 비밀번호가 틀림, 3 : 에러
        int loginResult = loginInfoService.tryLogin(loginInfo.getLoginId(), loginInfo.getLoginPw());

        switch (loginResult)
        {
            case 0:
                loginInfo = loginInfoService.getLoginInfoByLoginId(loginInfo.getLoginId());
                session.setAttribute("LOGININFO", loginInfo);
                session.setAttribute("USERNAME", loginInfo.getUserInfo().getUserName());
                return "redirect:/main";
            case 1:
            case 2:
                return "redirect:/login?login=fail";
            default:
                return "redirect:/login?login=error";
        }
    }
}
