package io.github.bxo.captcha.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CaptchaController {

    // inject via application.properties
    @Value("${captcha.secret}")
    private String secret;

    @PostMapping("/captcha")
    public String welcome(Map<String, Object> model, @RequestParam("g-recaptcha-response") String response) {
        if(StringUtils.isEmpty(response)){
            model.put("message", "Fail");
        }else{
            model.put("secret", secret);
            model.put("response", response);
            model.put("remoteip", "127.0.0.1");
            return "verify";
        }

        return "welcome";
    }

}
