package by.pvt.module3.command.crew;

import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.StaffService;

import javax.servlet.http.HttpServletRequest;

public class InsertMemberCommand extends CrewCommand{

    @Override
    public String execute(HttpServletRequest request) {

        Crew crew = getService().getById(Integer.parseInt(request.getParameter(ID).trim()));

        StaffService staffService = new StaffService();
        Staff staff = staffService.getById(Integer.parseInt(request.getParameter(Crew.STAFF_ID).trim()));
        crew.getMembers().add(staff);

        try {
            getService().update(crew);
        } catch (Exception e) {
            handleException(e, request);
        }

        initEditAttributes(crew, request);
        request.setAttribute(ENTITY_EDIT, crew);
        return ConfigurationManager.getProperty(getPropPathEdit());
    }
}
