package by.pvt.module3.command.crew;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.dao.CrewDAO;
import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.resource.ConfigurationManager;

public class UpdateCrewCommand implements ActionCommand {

	private static final DateFormat DF = new SimpleDateFormat("dd.MM.yyyy");

	@Override
	public String execute(HttpServletRequest request) {
		try {
			Crew crew = CrewDAO.getInstance().getCrew(Integer.parseInt(request.getParameter(Crew.ID).trim()));

			crew.setCreateDate(DF.parse(request.getParameter(Crew.CREATE_DATE).trim()));
			crew.setReady(Integer.parseInt(request.getParameter(Crew.READY).trim()));

			HttpSession session = request.getSession();
			Integer user_id = (Integer) session.getAttribute("user_id");
			crew.setUser(UserDAO.getInstance().getUser(user_id));

			CrewDAO.getInstance().updateCrew(crew);

			List<Crew> list = CrewDAO.getInstance().getAllCrews();
			request.setAttribute("crew", list);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ConfigurationManager.getProperty("path.page.crews");
	}
}
