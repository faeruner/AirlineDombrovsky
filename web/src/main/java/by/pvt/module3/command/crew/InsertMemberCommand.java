package by.pvt.module3.command.crew;

import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.resource.ConfigurationManager;
import by.pvt.module3.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class InsertMemberCommand extends CrewCommand{

    @Autowired
    StaffService staffService;

    @Override
    public String execute(Map<String, String> paramMap, Model model) {

        Crew crew = crewService.getById(Integer.parseInt(paramMap.get(ID).trim()));

        Staff staff = staffService.getById(Integer.parseInt(paramMap.get(Crew.STAFF_ID).trim()));
        crew.getMembers().add(staff);

        try {
            getService().update(crew);
        } catch (Exception e) {
            handleException(e, model);
        }

        initEditAttributes(crew, model);
        model.addAttribute(ENTITY_EDIT, crew);
        return ConfigurationManager.getProperty(getPropPathEdit());
    }
}
