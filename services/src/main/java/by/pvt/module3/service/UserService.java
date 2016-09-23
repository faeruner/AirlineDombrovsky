package by.pvt.module3.service;

import by.pvt.module3.dao.UserDAO;
import by.pvt.module3.entity.User;
import by.pvt.module3.service.common.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by v on 08.09.2016.
 */
@Service
@Transactional
public class UserService extends BaseService<User> {

//    public UserService() {
//        super(new UserDAO());
//    }

    public User getUserByLogin(String login) {
        UserDAO dao = (UserDAO) getDao();
        return dao.getUserByLogin(login);
    }
}
