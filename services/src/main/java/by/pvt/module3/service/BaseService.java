package by.pvt.module3.service;

import by.pvt.module3.dao.CommonDAO;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.hibernate.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v on 06.09.2016.
 */
public class BaseService<T> implements CommonService<T> {
    private static final Logger log = LogManager.getRootLogger();

    private Class clazz;
    private Integer recordsPerPage;

    public BaseService(Class clazz) {
        this.clazz = clazz;
        this.recordsPerPage = 3;
    }

    private Integer getRecordsPerPage() {
        return recordsPerPage;
    }

    public T getById(Integer id) {
        CommonDAO<T> dao = new CommonDAO<T>(clazz, HibernateUtil.getSesson());
        return dao.getById(id);
    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<T> dao = new CommonDAO<T>(clazz, session);
            dao.delete(id);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }

    }

    public void add(T entity) {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<T> dao = new CommonDAO<T>(clazz, session);
            dao.add(entity);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public void update(T entity) {
        Session session = HibernateUtil.getSesson();
        Transaction transaction = session.beginTransaction();
        try {
            CommonDAO<T> dao = new CommonDAO<T>(clazz, session);
            dao.update(entity);
            transaction.commit();
        } catch (HibernateException e) {
            log.error(e);
            transaction.rollback();
        }
    }

    public List<Integer> getPagesNums() {
        String hql = "select count(*) from " + HibernateUtil.getEntityByClass(clazz);
        Query queryCount = HibernateUtil.getSesson().createQuery(hql);
        Long count = (Long) queryCount.uniqueResult();
        List<Integer> listPages = new ArrayList<Integer>();
        for (int i = 1; count > 0; i++) {
            listPages.add(i);
            count -= getRecordsPerPage();
        }
        return listPages;
    }

    public List<T> getAll() {
        Session session = HibernateUtil.getSesson();
        CommonDAO<T> dao = new CommonDAO<T>(clazz, session);
        return dao.getAll();
    }

    public List<T> getPage(Integer numPage) {
        return getPage(numPage, "id");
    }

    public List<T> getPage(Integer numPage, String orderBy) {
        Session session = HibernateUtil.getSesson();
        String hql = new StringBuilder().append("from ").append(HibernateUtil.getEntityByClass(clazz)).append(" order by ").append(orderBy).toString();
        Query query = session.createQuery(hql);
        if (numPage == null)
            numPage = 1;

        query.setFirstResult((numPage - 1) * getRecordsPerPage());
        query.setMaxResults(getRecordsPerPage());

        return (List<T>) query.list();
    }
}
