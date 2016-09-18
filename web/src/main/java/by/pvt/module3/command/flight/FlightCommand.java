package by.pvt.module3.command.flight;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.entity.Flight;
import by.pvt.module3.service.AirlineService;
import by.pvt.module3.service.AirportService;
import by.pvt.module3.service.CrewService;
import by.pvt.module3.service.FlightService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public class FlightCommand extends BaseCommand<Flight> {

    private static final String LIST_DEPARTURE = "departure";
    private static final String LIST_ARRIVAL = "arrival";
    private static final String LIST_AIRLINE = "airline";
    private static final String LIST_CREW = "crew";

    public FlightCommand() {
        super(new FlightService(), "path.page.edit_flight", "path.page.flights");
    }

    @Override
    protected Flight getSelectedEntity(Integer id, HttpServletRequest request){
        Flight entity;
        if (id > 0) {
            entity = getService().getById(id);
        }
        else {
            entity = new Flight();
            entity.setCreateDate(new Date());
            entity.setUser(getSessionUser(request));
        }
        return entity;
    }

    @Override
    protected void initEditAttributes(Flight entity, HttpServletRequest request) {
        AirportService airportService = new AirportService();
        List<Airport> listAirports = airportService.getAll();
        request.setAttribute(LIST_DEPARTURE, listAirports);
        request.setAttribute(LIST_ARRIVAL, listAirports);

        AirlineService airlineService = new AirlineService();
        request.setAttribute(LIST_AIRLINE, airlineService.getAll());

        CrewService crewService = new CrewService();
        request.setAttribute(LIST_CREW, crewService.getAll());
    }

    protected void updateEntity(Flight flight, HttpServletRequest request){
        flight.setCode(request.getParameter(Flight.CODE).trim());
        try {
            flight.setDepDate(DF.parse(request.getParameter(Flight.DEPARTURE_DATE).trim()));
            flight.setReturnDate(DF.parse(request.getParameter(Flight.RETURN_DATE).trim()));
            flight.setCreateDate(DF.parse(request.getParameter(Flight.CREATE_DATE).trim()));
        } catch (ParseException e) {
            log.error(e);
        }

        AirportService airportService = new AirportService();
        AirlineService airlineService = new AirlineService();
        CrewService crewService = new CrewService();

        flight.setArrival(airportService.
                getById(Integer.parseInt(request.getParameter(Flight.AIRPORT_ARV_ID).trim())));
        flight.setDeparture(airportService.
                getById(Integer.parseInt(request.getParameter(Flight.AIRPORT_DEP_ID).trim())));
        flight.setAirline(airlineService.
                getById(Integer.parseInt(request.getParameter(Flight.AIRLINE_ID).trim())));
        flight.setCrew(crewService.
                getById(Integer.parseInt(request.getParameter(Flight.CREW_ID).trim())));
        flight.setUser(getSessionUser(request));
    }
}
