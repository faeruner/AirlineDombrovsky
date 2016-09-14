package by.pvt.module3.command.staff;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.MemberType;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.service.BaseService;
import by.pvt.module3.service.CommonService;
import by.pvt.module3.service.ServiceMemberType;
import by.pvt.module3.service.ServiceStaff;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by v on 08.09.2016.
 */
public class StaffCommand extends BaseCommand<Staff> {

    private static final String LIST_MEMBER_TYPE = "member_type";

    public StaffCommand() {
        super(new ServiceStaff(), "path.page.edit_staff", "path.page.staff");
    }

    @Override
    protected void initEditAttributes(Staff staff, HttpServletRequest request) {
        ServiceMemberType serviceMemberType = new ServiceMemberType();
        request.setAttribute(LIST_MEMBER_TYPE, serviceMemberType.getAll());
    }

    protected void updateEntity(Staff staff, HttpServletRequest request){
        ServiceMemberType serviceMemberType = new ServiceMemberType();

        staff.setName(request.getParameter(Staff.NAME).trim());
        staff.setSurname(request.getParameter(Staff.SURNAME).trim());
        staff.setMember(serviceMemberType.getById(Integer.parseInt(request.getParameter(Staff.MEMBER_TYPE_ID).trim())));
    }

}
