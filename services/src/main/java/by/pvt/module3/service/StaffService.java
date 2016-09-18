package by.pvt.module3.service;

import by.pvt.module3.dao.StaffDAO;
import by.pvt.module3.entity.Staff;
import by.pvt.module3.service.common.BaseService;

/**
 * Created by v on 07.09.2016.
 */
public class StaffService extends BaseService<Staff> {

    public StaffService() {
        super(new StaffDAO());
    }
}
