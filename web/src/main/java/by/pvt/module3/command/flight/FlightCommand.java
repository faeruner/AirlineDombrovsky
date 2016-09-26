package by.pvt.module3.command.flight;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Flight;
import by.pvt.module3.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by v on 08.09.2016.
 */
@Component
public class FlightCommand extends BaseCommand<Flight> {

    private static final String LIST_DEPARTURE = "departure";
    private static final String LIST_ARRIVAL = "arrival";
    private static final String LIST_AIRLINE = "airline";
    private static final String LIST_CREW = "crew";

    public FlightCommand() {
        super(Flight.class, "path.page.edit_flight", "path.page.flights");
    }

    @Override
    protected Flight getSelectedEntity(Integer id, Model model) {
        Flight entity;
        if (id > 0) {
            entity = getService().getById(Flight.class, id);
        }
        else {
            entity = new Flight();
            entity.setCreateDate(new Date());
            entity.setUser(getSessionUser(model));
        }
        return entity;
    }

    @Autowired
    CommonService<Airport> airportService;
    @Autowired
    CommonService<Airline> airlineService;
    @Autowired
    CommonService<Crew> crewService;

    @Override
    protected void initEditAttributes(Flight entity, Model model) {
        List<Airport> listAirports = airportService.getAll(Airport.class);
        model.addAttribute(LIST_DEPARTURE, listAirports);
        model.addAttribute(LIST_ARRIVAL, listAirports);
        model.addAttribute(LIST_AIRLINE, airlineService.getAll(Airline.class));
        model.addAttribute(LIST_CREW, crewService.getAll(Crew.class));
    }

    protected void updateEntity(Flight flight, Map<String, String> paramMap, Model model) {
        flight.setCode(paramMap.get(Flight.CODE).trim());
        try {
            flight.setDepDate(DF.parse(paramMap.get(Flight.DEPARTURE_DATE).trim()));
            flight.setReturnDate(DF.parse(paramMap.get(Flight.RETURN_DATE).trim()));
            flight.setCreateDate(DF.parse(paramMap.get(Flight.CREATE_DATE).trim()));
        } catch (ParseException e) {
            log.error(e);
        }

        flight.setArrival(airportService.
                getById(Airport.class, Integer.parseInt(paramMap.get(Flight.AIRPORT_ARV_ID).trim())));
        flight.setDeparture(airportService.
                getById(Airport.class, Integer.parseInt(paramMap.get(Flight.AIRPORT_DEP_ID).trim())));
        flight.setAirline(airlineService.
                getById(Airline.class, Integer.parseInt(paramMap.get(Flight.AIRLINE_ID).trim())));
        flight.setCrew(crewService.
                getById(Crew.class, Integer.parseInt(paramMap.get(Flight.CREW_ID).trim())));
        flight.setUser(getSessionUser(model));
    }
}
