package pl.pp.controllers.cloudapi;

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
import pl.pp.controllers.MainPageController;
import pl.pp.model.ParameterConstraints;

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

    //TODO: gps_position IS SPECIAL - HANDLE IT
    //TODO: odziabać step_rate

    @RequestMapping(value = "/parameter", method = RequestMethod.GET)
    public String getParameter(@RequestParam(value="parameterType", required=false, defaultValue="systolic_press") String parameterType,
                               Model model){
        String result = restTemplate.getForObject("http://health-keeper-api.gear.host/api/Measurement/params/"
                +dateTime+","+parameterType+"/1", String.class);
        log.info(result);
        ParameterConstraints constaint = ParameterConstraints.getParameterByName(parameterType);
        List<String> dates = new ArrayList<>();
        List<Double> parameters = new ArrayList<>();
        result = result.replaceAll("\\\\", "");
        result = result.substring(1, result.length()-1);
        log.info(result);
        JSONArray json = new JSONArray (result);
        for (int i = 0; i < json.length(); i++) {
            JSONObject j = json.getJSONObject(i);
            parameters.add(Double.parseDouble(j.getString(parameterType)));
            dates.add(j.get(dateTime).toString());
            log.info(j.toString());
        }
        model.addAttribute("labels", dates);
        model.addAttribute("result", parameters);
        model.addAttribute("min", constaint.getMinAcceptedValue());
        model.addAttribute("max", constaint.getMaxAcceptedValue());
        return "chartPage";
    }

    //public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model)

}
