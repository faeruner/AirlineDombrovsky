package by.pvt.module3.service.common;

import by.pvt.module3.entity.Fact;

import java.util.List;

/**
 * Created by v on 06.09.2016.
 */
public interface CommonService<T extends Fact> {
    T getById(Integer id);

    void delete(Integer id);

    Integer add(T entity);

    T update(T entity);

    List<T> getPage(Integer pageNum);

    List<T> getAll();

    List<Integer> getPagesNums();

    Integer getRecordsPerPage();

    void setRecordsPerPage(Integer recordsPerPage);

    Long getInsertPageNum();
}
