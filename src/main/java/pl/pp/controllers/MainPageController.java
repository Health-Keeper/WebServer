package pl.pp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dpp on 12/17/16.
 */
@Controller
public class MainPageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting(Model model) {
        model.addAttribute("name", "SIEMA XD");
        return "mainPage";
    }

}
