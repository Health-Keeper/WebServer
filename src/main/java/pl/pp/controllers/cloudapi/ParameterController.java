package pl.pp.controllers.cloudapi;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pl.pp.controllers.MainPageController;
import pl.pp.model.ParameterConstraints;
import pl.pp.model.Person;
import pl.pp.service.PersonService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpp on 12/18/16.
 */
@Controller
public class ParameterController {

    private final Logger log = LoggerFactory.getLogger(MainPageController.class);

    private final String dateTime = "measurement_date";

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private PersonService personService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/person/data", method = RequestMethod.GET)
    public String getParameter(@RequestParam(value="parameterType", required=false, defaultValue="systolic_press") String parameterType,
                               Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String name = auth.getName();
        String userId = name.charAt(name.length()-1)+"";
        String result = restTemplate.getForObject("http://health-keeper-api.gear.host/api/Measurement/params/"
                +dateTime+","+parameterType+"/"+userId, String.class);

        ParameterConstraints constraint = ParameterConstraints.getParameterByName(parameterType);
        List<String> dates = new ArrayList<>();
        List<Double> parameters = new ArrayList<>();
        result = result.replaceAll("\\\\", "");
        result = result.substring(1, result.length()-1);

        JSONArray json = new JSONArray (result);
        for (int i = 0; i < json.length(); i++) {
            JSONObject j = json.getJSONObject(i);
            parameters.add(Double.parseDouble(j.getString(parameterType)));
            dates.add(j.get(dateTime).toString());
        }

        String accident = restTemplate.getForObject("http://health-keeper-api.gear.host/api/PersonAccident/"+userId, String.class);

        accident = accident.replaceAll("\\\\", "");
        accident = accident.substring(1, accident.length()-1);
        JSONObject accidentJson = new JSONObject (accident);

        model.addAttribute("parameterName", constraint.getParameterName().replace("_", " "));
        model.addAttribute("person", "/person/data");
        model.addAttribute("labels", dates);
        model.addAttribute("result", parameters);
        model.addAttribute("min", constraint.getMinAcceptedValue());
        model.addAttribute("max", constraint.getMaxAcceptedValue());
        model.addAttribute("accident", accidentJson);
        return "chartPage";
    }

    //public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model)

}
