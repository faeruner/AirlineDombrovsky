package by.pvt.module3.dao.common;

import java.util.ArrayList;
import java.util.List;

public interface CommonDAO<T> {

    Integer add(T entity);

    void delete(Integer id);

    void update(T entity);

    T getById(Integer id);

    ArrayList<T> getAll();

    Long getCount();

    List<T> getPage(Integer pageNum, Integer recordsPerPage);

    List<T> getPage(Integer pageNum, Integer recordsPerPage, String orderBy);
}
