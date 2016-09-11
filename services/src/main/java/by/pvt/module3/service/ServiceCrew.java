package by.pvt.module3.service;

import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.Crew;
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
public class ServiceCrew extends BaseService implements CommonService<Crew> {
    public Crew getById(Integer id)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<Crew> dao = new CommonDAO<Crew>(Crew.class, session);
            transaction.commit();
            return dao.getById(id);
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
        return null;
    }

    public void delete(Integer id)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<Crew> dao = new CommonDAO<Crew>(Crew.class, session);
            dao.delete(id);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public void add(Crew air)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<Crew> dao = new CommonDAO<Crew>(Crew.class, session);
            dao.add(air);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public void update(Crew air)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<Crew> dao = new CommonDAO<Crew>(Crew.class, session);
            dao.update(air);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public List<Crew> getPage(Integer num_page)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        List<Crew> list = null;
        try{
            Query query = session.createQuery("from by.pvt.module3.entity.Crew order by id");
            if(num_page == null)
                num_page = 1;

            query.setFirstResult((num_page-1)*getRecordsPerPage());
            query.setMaxResults(getRecordsPerPage());
            list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
        return list;
    }

    public List<Crew> getAll() {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try{
            CommonDAO<Crew> dao = new CommonDAO<Crew>(Crew.class, session);
            transaction.commit();
            return dao.getAll();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
        return null;
    }

    public List<Integer> getPagesNums()
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try{
            Query queryCount = session.createQuery("select count(*) from by.pvt.module3.entity.Crew");
            Long count = (Long) queryCount.uniqueResult();
            List<Integer> listPages = new ArrayList<Integer>();
            for(int i=1; count>0; i++) {
                listPages.add(i);
                count -= getRecordsPerPage();
            }
            transaction.commit();
            return listPages;
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
        return null;
    }
}
