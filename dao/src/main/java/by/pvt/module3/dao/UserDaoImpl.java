package by.pvt.module3.dao;

import by.pvt.module3.dao.common.BaseDao;
import by.pvt.module3.dao.common.SessionUtil;
import by.pvt.module3.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    public User getUserByLogin(String login) {
        Session session = SessionUtil.getSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq(User.LOGIN, login));
        return (User) criteria.uniqueResult();
    }
}
