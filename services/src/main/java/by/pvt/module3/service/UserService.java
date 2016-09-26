package by.pvt.module3.service;

import by.pvt.module3.dao.common.CommonDAO;
import by.pvt.module3.dao.common.UserDAOCommon;
import by.pvt.module3.entity.User;
import by.pvt.module3.service.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserDAOCommon dao;

    @Override
    protected CommonDAO<User> getDao() {
        return dao;
    }

    public User getUserByLogin(String login) {
//        UserDAOCommon dao = getDao();
        return dao.getUserByLogin(login);
    }
}
