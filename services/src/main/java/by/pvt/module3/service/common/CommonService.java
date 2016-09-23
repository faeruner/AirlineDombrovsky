package by.pvt.module3.service.common;

import java.util.List;

/**
 * Created by v on 06.09.2016.
 */
public interface CommonService<T> {
    T getById(Class clazz, Integer id);

    void delete(Class clazz, Integer id);

    Integer add(T entity);

    void update(T entity);

    List<T> getPage(Class clazz, Integer pageNum);

    List<T> getAll(Class clazz);

    List<Integer> getPagesNums(Class clazz);

    Integer getRecordsPerPage();

    void setRecordsPerPage(Integer recordsPerPage);

    Long getInsertPageNum(Class clazz);
}
