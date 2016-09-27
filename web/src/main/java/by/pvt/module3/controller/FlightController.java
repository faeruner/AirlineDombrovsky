package by.pvt.module3.controller;

import by.pvt.module3.controller.common.ControllerUtils;
import by.pvt.module3.entity.*;
import by.pvt.module3.service.common.CommonService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/controller/flight", method = {RequestMethod.GET, RequestMethod.POST})
public class FlightController {
    private Logger log = LogManager.getLogger(FlightController.class);
    private static final String LIST_DEPARTURE = "departure";
    private static final String LIST_ARRIVAL = "arrival";
    private static final String LIST_AIRLINE = "airline";
    private static final String LIST_CREW = "crew";

    @Autowired
    private ControllerUtils<Flight> utils;
    @Autowired
    private CommonService<Flight> flightService;
    @Autowired
    private CommonService<Airport> airportService;
    @Autowired
    private CommonService<Airline> airlineService;
    @Autowired
    private CommonService<Crew> crewService;

    @PostConstruct
    public void init() {
        utils.init("path.page.edit_flight", "path.page.flights", Flight.class, flightService);
    }

    @RequestMapping
    public String perform(@RequestParam Map<String, String> paramMap, Model model, HttpSession httpSession) {
        List<Airport> listAirports = airportService.getAll(Airport.class);
        model.addAttribute(LIST_DEPARTURE, listAirports);
        model.addAttribute(LIST_ARRIVAL, listAirports);
        model.addAttribute(LIST_AIRLINE, airlineService.getAll(Airline.class));
        model.addAttribute(LIST_CREW, crewService.getAll(Crew.class));
        model.addAttribute(ControllerUtils.ENTITY, updateEntity(utils.findById(paramMap, model), paramMap, utils.getSessionUser(httpSession, model)));
        return utils.getPage(paramMap, model);
    }

    protected Flight updateEntity(Flight flight, Map<String, String> paramMap, User user) {
        if (flight == null)
            flight = new Flight();

        if (paramMap.containsKey(Flight.CODE)) flight.setCode(paramMap.get(Flight.CODE).trim());
        try {
            if (paramMap.containsKey(Flight.DEPARTURE_DATE))
                flight.setDepDate(utils.DF.parse(paramMap.get(Flight.DEPARTURE_DATE).trim()));
            if (paramMap.containsKey(Flight.RETURN_DATE))
                flight.setReturnDate(utils.DF.parse(paramMap.get(Flight.RETURN_DATE).trim()));
            if (paramMap.containsKey(Flight.CREATE_DATE))
                flight.setCreateDate(utils.DF.parse(paramMap.get(Flight.CREATE_DATE).trim()));
            else flight.setCreateDate(new Date());
        } catch (ParseException e) {
            log.error(e);
        }

        if (paramMap.containsKey(Flight.AIRPORT_ARV_ID)) flight.setArrival(airportService.
                getById(Airport.class, utils.getParamIntDef(paramMap, Flight.AIRPORT_ARV_ID, -1)));
        if (paramMap.containsKey(Flight.AIRPORT_DEP_ID)) flight.setDeparture(airportService.
                getById(Airport.class, utils.getParamIntDef(paramMap, Flight.AIRPORT_DEP_ID, -1)));
        if (paramMap.containsKey(Flight.AIRLINE_ID)) flight.setAirline(airlineService.
                getById(Airline.class, utils.getParamIntDef(paramMap, Flight.AIRLINE_ID, -1)));
        if (paramMap.containsKey(Flight.CREW_ID)) flight.setCrew(crewService.
                getById(Crew.class, utils.getParamIntDef(paramMap, Flight.CREW_ID, -1)));

        flight.setUser(user);

        return flight;
    }
}

