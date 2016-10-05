package by.pvt.module3.service;

import by.pvt.module3.entity.User;
import by.pvt.module3.service.common.CommonService;

public interface UserService extends CommonService<User> {
    User getUserByLogin(String login);
}
