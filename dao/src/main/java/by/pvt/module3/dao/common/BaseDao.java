package by.pvt.module3.dao.common;

import by.pvt.module3.entity.Fact;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class BaseDao<T extends Fact> implements CommonDao<T> {
    protected final static Logger log = LogManager.getRootLogger();
    private final static String ID = "id";
    private final Class<T> clazz;

    public BaseDao() {
        this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Integer add(T entity) {
        Session session = SessionUtil.getSession();
        Integer id = (Integer) session.save(entity);
        session.flush();
        return id;
    }

    protected void prepareDelete(T t) {

    }

    public void delete(Integer id) {
        Session session = SessionUtil.getSession();
        T t = (T) session.get(clazz, id);
        prepareDelete(t);
        session.delete(t);
        session.flush();
    }

    public T update(T entity) {
        Session session = SessionUtil.getSession();
        session.update(entity);
        session.flush();
        entity = (T) session.get(clazz, entity.getId());
        return entity;
    }

    public T getById(Integer id) {
        Session session = SessionUtil.getSession();
        T t = (T) session.load(clazz, id);
        return t;
    }

    public ArrayList<T> getAll() {
        Session session = SessionUtil.getSession();
        ArrayList<T> listT = (ArrayList<T>) session.createCriteria(clazz).list();
        return listT;
    }

    public Long getCount() {
        Session session = SessionUtil.getSession();
        String hql = "select count(*) from " + SessionUtil.getEntityByClass(clazz);
        Query queryCount = session.createQuery(hql);
        return (Long) queryCount.uniqueResult();
    }

    public List<T> getPage(Integer pageNum, Integer recordsPerPage) {
        return getPage(pageNum, recordsPerPage, ID);
    }

    public List<T> getPage(Integer pageNum, Integer recordsPerPage, String orderBy) {
        Session session = SessionUtil.getSession();
        String hql = new StringBuilder()
                .append("from ")
                .append(SessionUtil.getEntityByClass(clazz))
                .append(" order by ").append(orderBy).toString();
        Query query = session.createQuery(hql);
        if (pageNum == null)
            pageNum = 1;
        query.setMaxResults(recordsPerPage);
        query.setFirstResult((pageNum - 1) * recordsPerPage);
        return (List<T>) query.list();
    }
}
