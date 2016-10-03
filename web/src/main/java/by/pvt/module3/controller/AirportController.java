package by.pvt.module3.controller;

import by.pvt.module3.controller.common.CommonController;
import by.pvt.module3.entity.Airport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(value = "/controller/airport", method = {RequestMethod.GET, RequestMethod.POST})
public class AirportController extends CommonController<Airport> {

    public AirportController() {
        super("path.page.edit_airport", "path.page.airports");
    }

    @RequestMapping
    public String perform(@RequestParam Map<String, String> paramMap, Model model) {
        model.addAttribute(CommonController.ENTITY, updateEntity(findById(paramMap, model), paramMap));
        return getPage(paramMap, model);
    }

    protected Airport updateEntity(Airport airport, Map<String, String> paramMap) {
        if (airport == null)
            airport = new Airport();
        if (paramMap.containsKey(Airport.NAME))
            airport.setName(paramMap.get(Airport.NAME).trim());
        return airport;
    }
}
