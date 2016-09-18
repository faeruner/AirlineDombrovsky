package by.pvt.module3.command.staff;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.service.MemberTypeService;
import by.pvt.module3.service.StaffService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by v on 08.09.2016.
 */
public class StaffCommand extends BaseCommand<Staff> {

    private static final String LIST_MEMBER_TYPE = "member_type";

    public StaffCommand() {
        super(new StaffService(), "path.page.edit_staff", "path.page.staff");
    }

    @Override
    protected void initEditAttributes(Staff staff, HttpServletRequest request) {
        MemberTypeService memberTypeService = new MemberTypeService();
        request.setAttribute(LIST_MEMBER_TYPE, memberTypeService.getAll());
    }

    protected void updateEntity(Staff staff, HttpServletRequest request){
        MemberTypeService memberTypeService = new MemberTypeService();

        staff.setName(request.getParameter(Staff.NAME).trim());
        staff.setSurname(request.getParameter(Staff.SURNAME).trim());
        staff.setMember(memberTypeService.getById(Integer.parseInt(request.getParameter(Staff.MEMBER_TYPE_ID).trim())));
    }

}
