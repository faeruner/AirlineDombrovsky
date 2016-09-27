package by.pvt.module3.controller;

import by.pvt.module3.controller.common.ControllerUtils;
import by.pvt.module3.entity.Airport;
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
@RequestMapping(value = "/controller/airport", method = {RequestMethod.GET, RequestMethod.POST})
public class AirportController {

    @Autowired
    private ControllerUtils<Airport> utils;

    @Autowired
    private CommonService<Airport> airportService;

    @PostConstruct
    public void init() {
        utils.init("path.page.edit_airport", "path.page.airports", Airport.class, airportService);
    }

    @RequestMapping
    public String perform(@RequestParam Map<String, String> paramMap, Model model) {
        model.addAttribute(ControllerUtils.ENTITY, updateEntity(utils.findById(paramMap, model), paramMap));
        return utils.getPage(paramMap, model);
    }

    protected Airport updateEntity(Airport airport, Map<String, String> paramMap) {
        if (airport == null)
            airport = new Airport();
        if (paramMap.containsKey(Airport.NAME))
            airport.setName(paramMap.get(Airport.NAME).trim());
        return airport;
    }
}
