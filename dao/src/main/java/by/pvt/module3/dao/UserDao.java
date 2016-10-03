package by.pvt.module3.dao;

import by.pvt.module3.dao.common.CommonDao;
import by.pvt.module3.entity.User;

public interface UserDao extends CommonDao<User> {
    User getUserByLogin(String login);
}
