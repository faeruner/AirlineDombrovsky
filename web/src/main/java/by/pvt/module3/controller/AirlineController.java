package by.pvt.module3.controller;

import by.pvt.module3.controller.common.ControllerUtils;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.Map;

@Controller
@RequestMapping(value = "/controller/airline", method = {RequestMethod.GET, RequestMethod.POST})
public class AirlineController {

    @Autowired
    private ControllerUtils<Airline> utils;

    @Autowired
    private CommonService<Airline> airlineService;

    @PostConstruct
    public void init() {
        utils.init("path.page.edit_airline", "path.page.airlines", Airline.class, airlineService);
    }

    @RequestMapping
    public String perform(@RequestParam Map<String, String> paramMap, Model model) {
        model.addAttribute(ControllerUtils.ENTITY, updateEntity(utils.findById(paramMap, model), paramMap));
        return utils.getPage(paramMap, model);
    }

    protected Airline updateEntity(Airline airline, Map<String, String> paramMap) {
        if (airline == null)
            airline = new Airline();
        if (paramMap.containsKey(Airline.NAME))
            airline.setName(paramMap.get(Airline.NAME).trim());
        return airline;
    }
}
