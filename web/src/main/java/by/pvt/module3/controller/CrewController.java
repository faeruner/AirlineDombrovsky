package by.pvt.module3.controller;

import by.pvt.module3.controller.common.CommonController;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.entity.User;
import by.pvt.module3.service.common.CommonService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/controller/crew", method = {RequestMethod.GET, RequestMethod.POST})
public class CrewController extends CommonController<Crew> {
    private Logger log = LogManager.getLogger(CrewController.class);
    private final static String CHECKED = "checked";
    private final static String STAFF = "staff";
    private final static String READY_NO = "readyNo";
    private final static String READY_YES = "readyYes";
    private static final String MEMBER = "member";

    public CrewController() {
        super("path.page.edit_crew", "path.page.crews");
    }

    @Autowired
    private CommonService<Staff> staffService;

    @RequestMapping
    public String perform(@RequestParam Map<String, String> paramMap, Model model, HttpSession httpSession) {
        Crew crew = updateEntity(findById(paramMap, model), paramMap, getSessionUser(httpSession, model));

        String memberCommand = paramMap.get(MEMBER);
        if (memberCommand != null) {
            switch (memberCommand) {
                case CommonController.COMMAND_ADD:
                    Staff staff = staffService.getById(Integer.parseInt(paramMap.get(Crew.STAFF_ID).trim()));
                    crew.getMembers().add(staff);
                    break;
                case CommonController.COMMAND_DEL:
                    Integer staff_id = Integer.parseInt(paramMap.get(Crew.STAFF_ID).trim());
                    for (Staff item : crew.getMembers()) {
                        if (item.getId().equals(staff_id)) {
                            crew.getMembers().remove(item);
                            break;
                        }
                    }
                    break;
            }
            update(crew, model);
        }
        List<Staff> staff = staffService.getAll();
        for (Staff member : crew.getMembers()) {
            staff.removeIf(s -> s.getId().equals(member.getId()));
        }
        model.addAttribute(STAFF, staff);

        String readyNo = CHECKED;
        String readyYes = "";
        if (crew.getReady() > 0) {
            readyNo = "";
            readyYes = CHECKED;
        }
        model.addAttribute(READY_NO, readyNo);
        model.addAttribute(READY_YES, readyYes);

        model.addAttribute(CommonController.ENTITY, crew);
        if (memberCommand != null) {
            return getEditPage(paramMap, model);
        } else {
            return getPage(paramMap, model);
        }

    }

    protected Crew updateEntity(Crew crew, Map<String, String> paramMap, User user) {
        if (crew == null) {
            crew = new Crew();
            crew.setMembers(new HashSet<Staff>());
            crew.setCreateDate(new Date());
        }
        try {
            if (paramMap.containsKey(Crew.CREATE_DATE))
                crew.setCreateDate(DF.parse(paramMap.get(Crew.CREATE_DATE).trim()));
        } catch (ParseException e) {
            log.error(e);
        }
        crew.setReady(getParamIntDef(paramMap, Crew.READY, 0));
        crew.setUser(user);
        return crew;
    }
}
