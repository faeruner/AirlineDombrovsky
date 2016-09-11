package by.pvt.module3.command.crew;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceCrew;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteCrewCommand extends CrewCommand {

    @Override
    public String execute(HttpServletRequest request) {
        CommonService serviceCrew = new ServiceCrew();
        serviceCrew.delete(Integer.parseInt(request.getParameter(Crew.ID).trim()));

        return getPage(request, serviceCrew);
    }
}
