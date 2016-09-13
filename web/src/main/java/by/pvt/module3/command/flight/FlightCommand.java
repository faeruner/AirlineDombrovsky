package by.pvt.module3.command.flight;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Flight;
import by.pvt.module3.service.*;

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
        super(new BaseService<Flight>(Flight.class), "path.page.edit_flight", "path.page.flights");
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
        CommonService<Airport> serviceAirport = new ServiceAirport();
        List<Airport> listAirports = serviceAirport.getAll();
        request.setAttribute(LIST_DEPARTURE, listAirports);
        request.setAttribute(LIST_ARRIVAL, listAirports);

        CommonService<Airline> serviceAirline = new ServiceAirline();
        request.setAttribute(LIST_AIRLINE, serviceAirline.getAll());

        CommonService<Crew> serviceCrew = new ServiceCrew();
        request.setAttribute(LIST_CREW, serviceCrew.getAll());
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

        CommonService<Airport> serviceAirport = new ServiceAirport();
        CommonService<Airline> serviceAirline = new ServiceAirline();
        CommonService<Crew> serviceCrew = new ServiceCrew();

        flight.setArrival(serviceAirport.
                getById(Integer.parseInt(request.getParameter(Flight.AIRPORT_ARV_ID).trim())));
        flight.setDeparture(serviceAirport.
                getById(Integer.parseInt(request.getParameter(Flight.AIRPORT_DEP_ID).trim())));
        flight.setAirline(serviceAirline.
                getById(Integer.parseInt(request.getParameter(Flight.AIRLINE_ID).trim())));
        flight.setCrew(serviceCrew.
                getById(Integer.parseInt(request.getParameter(Flight.CREW_ID).trim())));
        flight.setUser(getSessionUser(request));
    }
}
