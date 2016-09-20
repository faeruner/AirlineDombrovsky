package by.pvt.module3.dao;

import by.pvt.module3.dao.common.BaseDAO;
import by.pvt.module3.dao.common.SessionUtil;
import by.pvt.module3.entity.Crew;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CrewDAO extends BaseDAO<Crew> {
    public CrewDAO() {
        super(Crew.class);
    }

    public void delete(Integer id) {
        Session session = SessionUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            Crew t = (Crew) session.get(Crew.class, id);
            t.getMembers().clear();
            session.delete(t);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }
}
