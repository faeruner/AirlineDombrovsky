package by.pvt.module3.command.crew;

import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.resource.ConfigurationManager;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class DeleteMemberCommand extends CrewCommand{

    @Override
    public String execute(Map<String, String> paramMap, Model model) {
        Crew crew = crewService.getById(Integer.parseInt(paramMap.get(ID).trim()));
        Integer staff_id = Integer.parseInt(paramMap.get(Crew.STAFF_ID).trim());
        for (Staff staff : crew.getMembers()) {
            if (staff.getId().equals(staff_id)) {
                crew.getMembers().remove(staff);
                break;
            }
        }

        try {
            getService().update(crew);
        } catch (Exception e) {
            handleException(e, model);
        }

        initEditAttributes(crew, model);
        model.addAttribute(ENTITY_EDIT, crew);
        Integer num_page = 1;
        if (paramMap.get(PAGE_NUM) != null)
            num_page = Integer.parseInt(paramMap.get(PAGE_NUM).trim());
        model.addAttribute(CURRENT_PAGE, num_page);
        return ConfigurationManager.getProperty(getPropPathEdit());
    }

}
