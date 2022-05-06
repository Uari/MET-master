package secuwow.MET.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import secuwow.MET.service.TrainingUserInfoService;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InteractionController {

    private final TrainingUserInfoService trainingUserInfoService;

    @RequestMapping("/interactionPage")
    public String interactionPage() {
        return "/interactionPage";
    }

    @GetMapping("/displayUpdate")
    public String displayInteraction(HttpServletRequest request) {
        String code = request.getParameter("EncryptionCode");

        if(code != null)
            trainingUserInfoService.insertInteractionDisplay(code);

        return "redirect:/interactionPage";
    }

    @GetMapping("/clickUpdate")
    public String clickInteraction(HttpServletRequest request) {
        String code = request.getParameter("EncryptionCode");

        if(code != null)
            trainingUserInfoService.insertInteractionClick(code);

        return "redirect:/interactionPage";
    }
}
