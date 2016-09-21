package by.pvt.module3.dao.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BaseDAO<T> implements CommonDAO<T> {
    protected final static Logger log = LogManager.getRootLogger();
    private Class persistentClass;
    private final static String ID = "id";

    public BaseDAO(Class persistentClass) {
        this.persistentClass = persistentClass;
    }

    private Class getPersistentClass() {
        return persistentClass;
    }

    public Integer add(T entity) {
        Transaction tx = null;
        Integer id = null;
        try {
            Session session = SessionUtil.getSesson();
            tx = session.beginTransaction();
            id = (Integer) session.save(entity);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e, tx);
        }
        return id;
    }

    protected void prepareDelete(T t) {

    }

    public void delete(Integer id) {
        Transaction tx = null;
        try {
            Session session = SessionUtil.getSesson();
            tx = session.beginTransaction();
            T t = (T) session.get(getPersistentClass(), id);
            prepareDelete(t);
            session.delete(t);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e, tx);
        }
    }

    public void update(T entity) {
        Transaction tx = null;
        try {
            Session session = SessionUtil.getSesson();
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e, tx);
        }
    }

    public T getById(Integer id) {
        Transaction tx = null;
        T t = null;
        try {
            Session session = SessionUtil.getSesson();
            tx = session.beginTransaction();
            t = (T) session.load(getPersistentClass(), id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e, tx);
        }
        return t;
    }

    public ArrayList<T> getAll() {
        Transaction tx = null;
        ArrayList<T> listT = null;
        try {
            Session session = SessionUtil.getSesson();
            tx = session.beginTransaction();
            listT = (ArrayList<T>) session.createCriteria(getPersistentClass()).list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e, tx);
        }
        return listT;
    }

    public Long getCount() {
        Transaction tx = null;
        Long count = 0L;
        try {
            Session session = SessionUtil.getSesson();
            tx = session.beginTransaction();
            String hql = "select count(*) from " + SessionUtil.getEntityByClass(getPersistentClass());
            Query queryCount = session.createQuery(hql);
            count = (Long) queryCount.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e, tx);
        }
        return count;
    }

    public List<T> getPage(Integer pageNum, Integer recordsPerPage) {
        return getPage(pageNum, recordsPerPage, ID);
    }

    public List<T> getPage(Integer pageNum, Integer recordsPerPage, String orderBy) {
        Transaction tx = null;
        List<T> listT = null;
        try {
            Session session = SessionUtil.getSesson();
            tx = session.beginTransaction();
            String hql = new StringBuilder().append("from ").append(SessionUtil.getEntityByClass(getPersistentClass())).append(" order by ").append(orderBy).toString();
            Query query = session.createQuery(hql);
            if (pageNum == null)
                pageNum = 1;

            if (!SessionUtil.driverUsed(SessionUtil.SQLITE_JDBC) || pageNum == 1) {
                query.setFirstResult((pageNum - 1) * recordsPerPage);
                query.setMaxResults(recordsPerPage);
            } else {
                query.setMaxResults((pageNum - 1) * recordsPerPage);
                query.setFirstResult(recordsPerPage);
            }
            listT = (List<T>) query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e, tx);
        }
        return listT;
    }

    protected void handleException(HibernateException e, Transaction tx) {
        log.error(e);
        if (tx != null) {
            SessionUtil.rollback(tx);
        }
        SessionUtil.closeSession();
        throw e;
    }
}
