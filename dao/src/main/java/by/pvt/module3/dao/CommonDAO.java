package by.pvt.module3.dao;

import by.pvt.module3.hibernate.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CommonDAO<T> {
    protected static final Logger log = LogManager.getRootLogger();
    private Class persistentClass;
    private Session session;

    public CommonDAO(Class persistentClass, Session session) {
        this.persistentClass = persistentClass;
        this.session = session;
        //this.persistentClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class getPersistentClass() {
        return persistentClass;
    }

    public Integer add(T entity) {
        return (Integer) session.save(entity);
    }

    public void delete(Integer id) {
        T t = (T) session.get(getPersistentClass(), id);
        session.delete(t);
    }

    public void update(T entity) {
        session.update(entity);
    }

    public T getById(Integer id) {
        T entity = null;
        entity = (T) session.get(getPersistentClass(), id);
        return entity;
    }

    public ArrayList<T> getAll() {
        ArrayList<T> entities = new ArrayList<T>();
        entities = (ArrayList<T>) session.createCriteria(getPersistentClass()).list();
        return entities;
    }
}
