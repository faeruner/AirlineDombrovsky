package by.pvt.module3.controller;

import by.pvt.module3.controller.common.ControllerUtils;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.Map;

@Controller
@RequestMapping(value = "controller/airline", method = RequestMethod.POST)
public class AirlineController {

    @Autowired
    ControllerUtils<Airline> controller;

    @Autowired
    private BaseService<Airline> airlineService;

    @PostConstruct
    public void init() {
        controller.init("path.page.airline", "path.page.airlines", Airline.class, airlineService);
    }

    @RequestMapping("list")
    public String showList(@RequestParam Map<String, String> paramMap, Model model) {
        return controller.fillModelPage(paramMap, model);
    }

    @RequestMapping("edit")
    private String edit(@RequestParam Map<String, String> paramMap, Model model) {
        return controller.fillModelEntity(paramMap, model);
    }

    @RequestMapping("add")
    private String add(@RequestParam Map<String, String> paramMap, Model model) {
        return controller.insert(new Airline(paramMap.get(Airline.NAME).trim()), paramMap, model);
    }

    @RequestMapping("del")
    private String delete(@RequestParam Map<String, String> paramMap, Model model) {
        return controller.delete(paramMap, model);
    }

    @RequestMapping(value = "upd", method = {RequestMethod.POST})
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
