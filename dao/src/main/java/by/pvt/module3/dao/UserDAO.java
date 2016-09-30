package by.pvt.module3.dao;

import by.pvt.module3.dao.common.BaseDAO;
import by.pvt.module3.dao.common.SessionUtil;
import by.pvt.module3.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends BaseDAO<User> implements UserDAOCommon {

    public User getUserByLogin(String login) {
        Session session = SessionUtil.getSesson();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq(User.LOGIN, login));
        User entity = (User) criteria.uniqueResult();
        return entity;
    }
}
