package by.pvt.module3.controller;

import by.pvt.module3.controller.common.ControllerUtils;
import by.pvt.module3.entity.MemberType;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.Map;

@Controller
@RequestMapping(value = "/controller/staff", method = {RequestMethod.GET, RequestMethod.POST})
public class StaffController {

    private static final String LIST_MEMBER_TYPE = "member_type";

    @Autowired
    private ControllerUtils<Staff> utils;

    @Autowired
    private CommonService<Staff> staffService;

    @Autowired
    private CommonService<MemberType> memberTypeService;

    @PostConstruct
    public void init() {
        utils.init("path.page.edit_staff", "path.page.staff", Staff.class, staffService);
    }

    @RequestMapping
    public String perform(@RequestParam Map<String, String> paramMap, Model model) {
        model.addAttribute(LIST_MEMBER_TYPE, memberTypeService.getAll(MemberType.class));
        model.addAttribute(ControllerUtils.ENTITY, updateEntity(utils.findById(paramMap, model), paramMap));
        return utils.getPage(paramMap, model);
    }

    protected Staff updateEntity(Staff staff, Map<String, String> paramMap) {
        if (staff == null)
            staff = new Staff();
        if (paramMap.containsKey(Staff.NAME)) staff.setName(paramMap.get(Staff.NAME).trim());
        if (paramMap.containsKey(Staff.SURNAME)) staff.setSurname(paramMap.get(Staff.SURNAME).trim());
        if (paramMap.containsKey(Staff.MEMBER_TYPE_ID))
            staff.setMember(memberTypeService.getById(MemberType.class, utils.getParamIntDef(paramMap, Staff.MEMBER_TYPE_ID, 1)));
        return staff;
    }
}
