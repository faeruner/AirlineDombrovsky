package by.pvt.module3.controller;

import by.pvt.module3.controller.common.CommonController;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.service.UserService;
import by.pvt.module3.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(value = "/controller/airline", method = {RequestMethod.GET, RequestMethod.POST})
public class AirlineController extends CommonController<Airline> {

    @Autowired
    public AirlineController(UserService userService, CommonService<Airline> commonService) {
        super("path.page.edit_airline", "path.page.airlines", userService, commonService);
    }

    @RequestMapping
    public String perform(@RequestParam Map<String, String> paramMap, Model model) {
        model.addAttribute(ENTITY, updateEntity(findById(paramMap, model), paramMap));
        return getPage(paramMap, model);
    }

    private Airline updateEntity(Airline airline, Map<String, String> paramMap) {
        if (airline == null)
            airline = new Airline();
        if (paramMap.containsKey(Airline.NAME))
            airline.setName(paramMap.get(Airline.NAME).trim());
        return airline;
    }
}
