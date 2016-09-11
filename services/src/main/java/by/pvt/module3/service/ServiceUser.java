package by.pvt.module3.service;

import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.User;
import by.pvt.module3.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
public class ServiceUser extends BaseService implements CommonService<User> {
    public User getById(Integer id)
    {
        CommonDAO<User> dao = new CommonDAO<User>(User.class, HibernateUtil.getSesson());
        return dao.getById(id);
    }

    public void delete(Integer id)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<User> dao = new CommonDAO<User>(User.class, session);
            dao.delete(id);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public void add(User air)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<User> dao = new CommonDAO<User>(User.class, session);
            dao.add(air);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public void update(User air)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<User> dao = new CommonDAO<User>(User.class, session);
            dao.update(air);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public List<User> getPage(Integer num_page)
    {
        Session session = HibernateUtil.getSesson();
        Query query = session.createQuery("from by.pvt.module3.entity.User order by id");
        if(num_page == null)
            num_page = 1;

        query.setFirstResult((num_page-1)*getRecordsPerPage());
        query.setMaxResults(getRecordsPerPage());
        List<User> list = query.list();

        return list;
    }

    public List<User> getAll() {
        return null;
    }

    public List<Integer> getPagesNums()
    {
        Session session = HibernateUtil.getSesson();
        Query queryCount = session.createQuery("select count(*) from by.pvt.module3.entity.User");
        Long count = (Long) queryCount.uniqueResult();

        List<Integer> listPages = new ArrayList<Integer>();
        for(int i=1; count>0; i++) {
            listPages.add(i);
            count -= getRecordsPerPage();
        }

        return listPages;
    }
}
