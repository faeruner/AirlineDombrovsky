package by.pvt.module3.command.flight;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.Flight;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceFlight;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteFlightCommand extends FlightCommand {

    @Override
    public String execute(HttpServletRequest request) {
        CommonService serviceFlight = new ServiceFlight();
        serviceFlight.delete(Integer.parseInt(request.getParameter(Flight.ID).trim()));

        return getPage(request, serviceFlight);
    }
}