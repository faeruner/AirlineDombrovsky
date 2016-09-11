package by.pvt.module3.service;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by v on 06.09.2016.
 */
public interface CommonService<T> {
    public T getById(Integer id);

    public void delete(Integer id);

    public void add(T entity);

    public void update(T entity);

    public List<T> getPage(Integer num_page);

    public List<T> getAll();

    public List<Integer> getPagesNums();
}
