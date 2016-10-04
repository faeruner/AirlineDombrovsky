package by.pvt.module3.service;

import by.pvt.module3.dao.UserDao;
import by.pvt.module3.entity.User;
import by.pvt.module3.service.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends BaseService<User> {
    @Autowired
    public UserService(UserDao dao) {
        super(dao);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUserByLogin(String login) {
        return ((UserDao) getDao()).getUserByLogin(login);
    }
}
