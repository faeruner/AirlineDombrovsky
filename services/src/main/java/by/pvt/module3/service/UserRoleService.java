package by.pvt.module3.service;

import by.pvt.module3.dao.UserRoleDAO;
import by.pvt.module3.entity.UserRole;
import by.pvt.module3.service.common.BaseService;

/**
 * Created by v on 08.09.2016.
 */
public class UserRoleService extends BaseService<UserRole> {

    public UserRoleService() {
        super(new UserRoleDAO());
    }
}
