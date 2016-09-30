package by.pvt.module3.dao;

import by.pvt.module3.dao.common.CommonDAO;
import by.pvt.module3.entity.User;

public interface UserDAOCommon extends CommonDAO<User> {
    User getUserByLogin(String login);
}
