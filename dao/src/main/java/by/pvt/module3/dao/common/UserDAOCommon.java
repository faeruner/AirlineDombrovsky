package by.pvt.module3.dao.common;

import by.pvt.module3.entity.User;

/**
 * Created by SBT-Dombrovsky-AE on 26.09.2016.
 */
public interface UserDAOCommon extends CommonDAO<User> {
    User getUserByLogin(String login);
}
