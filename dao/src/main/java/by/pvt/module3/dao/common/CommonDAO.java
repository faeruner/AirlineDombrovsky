package by.pvt.module3.dao.common;

import by.pvt.module3.entity.Fact;

import java.util.ArrayList;
import java.util.List;

public interface CommonDAO<T extends Fact> {

    Integer add(T entity);

    void delete(Class clazz, Integer id);

    T update(Class clazz, T entity);

    T getById(Class clazz, Integer id);

    ArrayList<T> getAll(Class clazz);

    Long getCount(Class clazz);

    List<T> getPage(Class clazz, Integer pageNum, Integer recordsPerPage);

    List<T> getPage(Class clazz, Integer pageNum, Integer recordsPerPage, String orderBy);
}
