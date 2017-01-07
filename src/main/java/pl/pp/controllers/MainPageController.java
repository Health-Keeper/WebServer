package pl.pp.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        String result = restTemplate.getForObject("http://health-keeper-api.gear.host/api/Measurement/params/alcohol,cholesterol/1", String.class);
        //model.addAttribute("name", "SIEMA XD");

        List<Double> alcoholParameters = new ArrayList<>();
        result = result.replaceAll("\\\\", "");
        result = result.replaceAll("\"", "");
        log.info(result);
        JSONArray json = new JSONArray (result);
        for (int i = 0; i < json.length(); i++) {
            JSONObject j = json.getJSONObject(i);
            alcoholParameters.add(j.getDouble("cholesterol"));
            log.info(j.toString());
        }
        model.addAttribute("result", alcoholParameters);
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
