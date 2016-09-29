package by.pvt.module3.service;

import by.pvt.module3.dao.UserDAOCommon;
import by.pvt.module3.dao.common.CommonDAO;
import by.pvt.module3.entity.User;
import by.pvt.module3.service.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by v on 08.09.2016.
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    UserDAOCommon dao;

    @Override
    protected CommonDAO<User> getDao() {
        return dao;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUserByLogin(String login) {
        return dao.getUserByLogin(login);
    }
}
