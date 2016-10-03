package by.pvt.module3.controller;

import by.pvt.module3.controller.common.CommonController;
import by.pvt.module3.entity.MemberType;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(value = "/controller/staff", method = {RequestMethod.GET, RequestMethod.POST})
public class StaffController extends CommonController<Staff> {

    private static final String LIST_MEMBER_TYPE = "member_type";

    @Autowired
    private CommonService<MemberType> memberTypeService;

    public StaffController() {
        super("path.page.edit_staff", "path.page.staff");
    }

    @RequestMapping
    public String perform(@RequestParam Map<String, String> paramMap, Model model) {
        model.addAttribute(LIST_MEMBER_TYPE, memberTypeService.getAll());
        model.addAttribute(CommonController.ENTITY, updateEntity(findById(paramMap, model), paramMap));
        return getPage(paramMap, model);
    }

    protected Staff updateEntity(Staff staff, Map<String, String> paramMap) {
        if (staff == null)
            staff = new Staff();
        if (paramMap.containsKey(Staff.NAME)) staff.setName(paramMap.get(Staff.NAME).trim());
        if (paramMap.containsKey(Staff.SURNAME)) staff.setSurname(paramMap.get(Staff.SURNAME).trim());
        if (paramMap.containsKey(Staff.MEMBER_TYPE_ID))
            staff.setMember(memberTypeService.getById(getParamIntDef(paramMap, Staff.MEMBER_TYPE_ID, 1)));
        return staff;
    }
}
