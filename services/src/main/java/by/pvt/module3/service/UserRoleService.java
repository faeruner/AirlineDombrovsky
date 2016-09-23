package by.pvt.module3.service;

import by.pvt.module3.entity.UserRole;
import by.pvt.module3.service.common.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
@Service
@Transactional
public class UserRoleService extends BaseService<UserRole> {

    //    public UserRoleService() {
//        super(new UserRoleDAO());
//    }
    public UserRole getById(Integer id) {
        return getById(UserRole.class, id);
    }

    public List<UserRole> getAll() {
        return getAll(UserRole.class);
    }
}
