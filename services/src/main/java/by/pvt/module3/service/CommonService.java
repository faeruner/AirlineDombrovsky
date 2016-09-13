package by.pvt.module3.service;

import java.util.List;

/**
 * Created by v on 06.09.2016.
 */
public interface CommonService<T> {
    T getById(Integer id);

    void delete(Integer id);

    void add(T entity);

    void update(T entity);

    List<T> getPage(Integer num_page);

    List<T> getAll();

    List<Integer> getPagesNums();
}
