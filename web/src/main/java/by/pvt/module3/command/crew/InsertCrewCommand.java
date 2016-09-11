package by.pvt.module3.command.crew;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.entity.User;
import by.pvt.module3.hibernate.HibernateUtil;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceCrew;
import by.pvt.module3.service.ServiceUser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class InsertCrewCommand extends CrewCommand {

    private static final DateFormat DF = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public String execute(HttpServletRequest request) {

        CommonService serviceCrew = new ServiceCrew();
        Crew crew = new Crew();

        try {
            crew.setCreateDate(DF.parse(request.getParameter(Crew.CREATE_DATE).trim()));
        } catch (ParseException e) {
            log.error(e);
        }
        crew.setReady(Integer.parseInt(request.getParameter(Crew.READY).trim()));

        CommonService serviceUser = new ServiceUser();
        HttpSession sessionHttp = request.getSession();

        Integer user_id = (Integer) sessionHttp.getAttribute("user_id");
        crew.setUser((User)serviceUser.getById(user_id));

        serviceCrew.add(crew);

        return getPage(request, serviceCrew);
    }
}
