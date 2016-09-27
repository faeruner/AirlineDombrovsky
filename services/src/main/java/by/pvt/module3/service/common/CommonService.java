package by.pvt.module3.service.common;

import by.pvt.module3.entity.Fact;

import java.util.List;

/**
 * Created by v on 06.09.2016.
 */
public interface CommonService<T extends Fact> {
    T getById(Class clazz, Integer id);

    void delete(Class clazz, Integer id);

    Integer add(T entity);

    T update(Class clazz, T entity);

    List<T> getPage(Class clazz, Integer pageNum);

    List<T> getAll(Class clazz);

    List<Integer> getPagesNums(Class clazz);

    Integer getRecordsPerPage();

    void setRecordsPerPage(Integer recordsPerPage);

    Long getInsertPageNum(Class clazz);
}
