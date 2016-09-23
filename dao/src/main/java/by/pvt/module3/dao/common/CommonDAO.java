package by.pvt.module3.dao.common;

import java.util.ArrayList;
import java.util.List;

public interface CommonDAO<T> {

    Integer add(T entity);

    void delete(Class clazz, Integer id);

    void update(T entity);

    T getById(Class clazz, Integer id);

    ArrayList<T> getAll(Class clazz);

    Long getCount(Class clazz);

    List<T> getPage(Class clazz, Integer pageNum, Integer recordsPerPage);

    List<T> getPage(Class clazz, Integer pageNum, Integer recordsPerPage, String orderBy);
}
