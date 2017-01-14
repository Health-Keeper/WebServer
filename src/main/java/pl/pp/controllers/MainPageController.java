package pl.pp.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pl.pp.service.dto.ParameterDto;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dpp on 12/17/16.
 */
@Controller
public class MainPageController {

    private final Logger log = LoggerFactory.getLogger(MainPageController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if (name.compareTo("anonymousUser") != 0) {
            String userId = name.charAt(name.length()-1)+"";
            String accident = restTemplate.getForObject("http://health-keeper-api.gear.host/api/PersonAccident/"+userId, String.class);
            accident = accident.replaceAll("\\\\", "");
            accident = accident.substring(1, accident.length()-1);

            JSONObject accidentJson = new JSONObject (accident);
            model.addAttribute("accident", accidentJson);
        }
        return "mainPage";
    }

    // Login form
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping(path = "/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
