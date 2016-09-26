package by.pvt.module3.command.crew;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.service.CrewService;
import by.pvt.module3.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by v on 08.09.2016.
 */
@Component
public class CrewCommand extends BaseCommand<Crew> {

    private final static String CHECKED = "checked";
    private final static String STAFF = "staff";
    private final static String READY_NO = "readyNo";
    private final static String READY_YES = "readyYes";

    public CrewCommand() {
        super(Crew.class, "path.page.edit_crew", "path.page.crews");
    }

    @Autowired
    CrewService crewService;

    @Override
    public CommonService<Crew> getService() {
        return crewService;
    }


    @Override
    protected Crew getSelectedEntity(Integer id, Model model) {
        Crew entity;
        if (id > 0) {
            entity = getService().getById(Crew.class, id);
        }
        else {
            entity = new Crew();
            entity.setCreateDate(new Date());
            entity.setReady(0);
            entity.setMembers(new HashSet<Staff>());
            entity.setUser(getSessionUser(model));
        }
        return entity;
    }

    @Autowired
    CommonService<Staff> staffService;

    @Override
    protected void initEditAttributes(Crew crew, Model model) {

        List<Staff> staff = staffService.getAll(Staff.class);
        for (Staff member : crew.getMembers()) {
            staff.remove(member);
        }

        String readyNo = CHECKED;
        String readyYes = "";
        if (crew.getReady() > 0) {
            readyNo = "";
            readyYes = CHECKED;
        }

        model.addAttribute(STAFF, staff);
        model.addAttribute(READY_NO, readyNo);
        model.addAttribute(READY_YES, readyYes);
    }

    protected void updateEntity(Crew crew, Map<String, String> paramMap, Model model) {
        try {
            crew.setCreateDate(DF.parse(paramMap.get(Crew.CREATE_DATE).trim()));
        } catch (ParseException e) {
            log.error(e);
        }
        crew.setReady(Integer.parseInt(paramMap.get(Crew.READY).trim()));
        crew.setUser(getSessionUser(model));
    }

}
