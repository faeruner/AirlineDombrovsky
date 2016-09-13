package by.pvt.module3.command.crew;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.service.BaseService;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceStaff;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public class CrewCommand extends BaseCommand<Crew> {

    private final static String CHECKED = "checked";
    private final static String STAFF = "staff";
    private final static String READY_NO = "readyNo";
    private final static String READY_YES = "readyYes";

    public CrewCommand() {
        super(new BaseService<Crew>(Crew.class), "path.page.edit_crew", "path.page.crews");
    }

    @Override
    protected Crew getSelectedEntity(Integer id, HttpServletRequest request){
        Crew entity;
        if (id > 0) {
            entity = getService().getById(id);
        }
        else {
            entity = new Crew();
            entity.setCreateDate(new Date());
            entity.setReady(0);
            entity.setMembers(new HashSet<Staff>());
            entity.setUser(getSessionUser(request));
        }
        return entity;
    }

    @Override
    protected void initEditAttributes(Crew crew, HttpServletRequest request) {
        CommonService<Staff> serviceStaff = new ServiceStaff();
        List<Staff> staff = serviceStaff.getAll();
        for (Staff member : crew.getMembers()) {
            staff.remove(member);
        }

        String readyNo = CHECKED;
        String readyYes = "";
        if (crew.getReady() > 0) {
            readyNo = "";
            readyYes = CHECKED;
        }

        request.setAttribute(STAFF, staff);
        request.setAttribute(READY_NO, readyNo);
        request.setAttribute(READY_YES, readyYes);
    }

    protected void updateEntity(Crew crew, HttpServletRequest request){
        try {
            crew.setCreateDate(DF.parse(request.getParameter(Crew.CREATE_DATE).trim()));
        } catch (ParseException e) {
            log.error(e);
        }
        crew.setReady(Integer.parseInt(request.getParameter(Crew.READY).trim()));
        crew.setUser(getSessionUser(request));
    }

}
