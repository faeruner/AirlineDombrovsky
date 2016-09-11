package by.pvt.module3.service;

import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 06.09.2016.
 */
public class ServiceAirport extends BaseService implements CommonService<Airport> {

    public Airport getById(Integer id)
    {
        CommonDAO<Airport> dao = new CommonDAO<Airport>(Airport.class, HibernateUtil.getSesson());
        return dao.getById(id);
    }

    public void delete(Integer id)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<Airport> dao = new CommonDAO<Airport>(Airport.class, session);
            dao.delete(id);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public void add(Airport air)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<Airport> dao = new CommonDAO<Airport>(Airport.class, session);
            dao.add(air);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public void update(Airport air)
    {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<Airport> dao = new CommonDAO<Airport>(Airport.class, session);
            dao.update(air);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public List<Airport> getPage(Integer num_page)
    {
        Session session = HibernateUtil.getSesson();
        Query query = session.createQuery("from by.pvt.module3.entity.Airport order by id");
        if(num_page == null)
            num_page = 1;

        query.setFirstResult((num_page-1)*getRecordsPerPage());
        query.setMaxResults(getRecordsPerPage());
        List<Airport> list = query.list();

        return list;
    }

    public List<Airport> getAll() {
        Session session = HibernateUtil.getSesson();
        CommonDAO<Airport> dao = new CommonDAO<Airport>(Airport.class, session);
        return dao.getAll();
    }

    public List<Integer> getPagesNums()
    {
        Session session = HibernateUtil.getSesson();
        Query queryCount = session.createQuery("select count(*) from by.pvt.module3.entity.Airport");
        Long count = (Long) queryCount.uniqueResult();

        List<Integer> listPages = new ArrayList<Integer>();
        for(int i=1; count>0; i++) {
            listPages.add(i);
            count -= getRecordsPerPage();
        }

        return listPages;
    }
}
