package by.pvt.module3.command.staff;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.MemberType;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

/**
 * Created by v on 08.09.2016.
 */
@Component
public class StaffCommand extends BaseCommand<Staff> {

    private static final String LIST_MEMBER_TYPE = "member_type";

    public StaffCommand() {
        super(Staff.class, "path.page.edit_staff", "path.page.staff");
    }

    @Autowired
    CommonService<MemberType> memberTypeService;

    @Override
    protected void initEditAttributes(Staff staff, Model model) {
        model.addAttribute(LIST_MEMBER_TYPE, memberTypeService.getAll(MemberType.class));
    }

    protected void updateEntity(Staff staff, Map<String, String> paramMap) {
        staff.setName(paramMap.get(Staff.NAME).trim());
        staff.setSurname(paramMap.get(Staff.SURNAME).trim());
        staff.setMember(memberTypeService.getById(MemberType.class, Integer.parseInt(paramMap.get(Staff.MEMBER_TYPE_ID).trim())));
    }

}
