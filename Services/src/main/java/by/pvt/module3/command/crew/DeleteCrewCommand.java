package by.pvt.module3.command.crew;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CrewDAO;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.resource.ConfigurationManager;

public class DeleteCrewCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            CrewDAO.getInstance().deleteCrew(Integer.parseInt(request.getParameter(Crew.ID).trim()));
            
            List<Crew> list = CrewDAO.getInstance().getAllCrews();
            request.setAttribute("crew", list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ConfigurationManager.getProperty("path.page.crews");
    }
}
