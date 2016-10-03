package by.pvt.module3.service;

import by.pvt.module3.dao.UserDao;
import by.pvt.module3.entity.User;
import by.pvt.module3.service.common.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by v on 08.09.2016.
 */
@Service
public class UserService extends BaseService<User> {

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUserByLogin(String login) {
        return ((UserDao) getDao()).getUserByLogin(login);
    }
}
