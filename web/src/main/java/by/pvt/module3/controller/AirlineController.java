package by.pvt.module3.controller;

import by.pvt.module3.entity.Airline;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AirlineController {
    MainController<Airline> controller;

    @Autowired
    private BaseService<Airline> airlineService;

    public AirlineController() {
        controller = new MainController<Airline>("path.page.airline", "path.page.airlines", Airline.class, airlineService);
    }

    @RequestMapping(value = "/controller/airline/list", method = {RequestMethod.GET})
    private String showList(@RequestParam Map<String, String> paramMap, Model model) {
        return controller.fillModelPage(paramMap, model);
    }

    @RequestMapping(value = "/controller/airline/edit", method = {RequestMethod.GET})
    private String edit(@RequestParam Map<String, String> paramMap, Model model) {
        return controller.fillModelEntity(paramMap, model);
    }

    @RequestMapping(value = "/controller/airline/add", method = {RequestMethod.POST})
    private String add(@RequestParam Map<String, String> paramMap, Model model) {
        return controller.insert(new Airline(paramMap.get(Airline.NAME).trim()), paramMap, model);
    }

    @RequestMapping(value = "/controller/airline/del", method = {RequestMethod.POST})
    private String delete(@RequestParam Map<String, String> paramMap, Model model) {
        return controller.delete(paramMap, model);
    }

    @RequestMapping(value = "/controller/airline/upd", method = {RequestMethod.POST})
    private String update(@RequestParam Map<String, String> paramMap, Model model) {
        Airline airline = controller.findById(paramMap, model);
        if (airline == null)
            return ConfigurationManager.getProperty("path.page.airline");
        updateEntity(airline, paramMap);
        return controller.update(airline, paramMap, model);
    }

    protected void updateEntity(Airline airline, Map<String, String> paramMap) {
        airline.setName(paramMap.get(Airline.NAME).trim());
    }
}
