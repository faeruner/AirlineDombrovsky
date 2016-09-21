package by.pvt.module3.command.crew;

import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class DeleteMemberCommand extends CrewCommand{

    @Override
    public String execute(HttpServletRequest request) {
        Crew crew = getService().getById(Integer.parseInt(request.getParameter(ID).trim()));
        Integer staff_id = Integer.parseInt(request.getParameter(Crew.STAFF_ID).trim());
        for (Staff staff : crew.getMembers()) {
            if (staff.getId().equals(staff_id)) {
                crew.getMembers().remove(staff);
                break;
            }
        }

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
