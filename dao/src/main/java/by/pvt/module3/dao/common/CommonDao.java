package by.pvt.module3.dao.common;

import by.pvt.module3.entity.Fact;

import java.util.ArrayList;
import java.util.List;

public interface CommonDao<T extends Fact> {

    Integer add(T entity);

    void delete(Integer id);

    T update(T entity);

    T getById(Integer id);

    ArrayList<T> getAll();

    Long getCount();

    List<T> getPage(Integer pageNum, Integer recordsPerPage);

    List<T> getPage(Integer pageNum, Integer recordsPerPage, String orderBy);
}
