package pl.pp.controllers.cloudapi;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PersonService personService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/person/{personId}/data", method = RequestMethod.GET)
    public String getParameter(@PathVariable long personId, @RequestParam(value="parameterType", required=false, defaultValue="systolic_press") String parameterType,
                               Model model){
        String result = restTemplate.getForObject("http://health-keeper-api.gear.host/api/Measurement/params/"
                +dateTime+","+parameterType+"/"+personId, String.class);
//        log.info(result);
        ParameterConstraints constraint = ParameterConstraints.getParameterByName(parameterType);
        List<String> dates = new ArrayList<>();
        List<Double> parameters = new ArrayList<>();
        result = result.replaceAll("\\\\", "");
        result = result.substring(1, result.length()-1);
//        log.info(result);
        JSONArray json = new JSONArray (result);
        for (int i = 0; i < json.length(); i++) {
            JSONObject j = json.getJSONObject(i);
            parameters.add(Double.parseDouble(j.getString(parameterType)));
            dates.add(j.get(dateTime).toString());
//            log.info(j.toString());
        }

        log.info(passwordEncoder.encode("12345"));

        model.addAttribute("parameterName", constraint.getParameterName().replace("_", " "));
        model.addAttribute("person", "/person/"+personId+"/data");
        model.addAttribute("labels", dates);
        model.addAttribute("result", parameters);
        model.addAttribute("min", constraint.getMinAcceptedValue());
        model.addAttribute("max", constraint.getMaxAcceptedValue());
        return "chartPage";
    }

    //public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model)

}
