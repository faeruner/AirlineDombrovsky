package by.pvt.module3.service.common;

import by.pvt.module3.entity.Fact;

import java.util.List;

public interface CommonService<T extends Fact> {
    T getById(Integer id);

    void delete(Integer id);

    Integer add(T entity);

    T update(T entity);

    List<T> getPage(Integer pageNum);

    List<T> getAll();

    List<Integer> getPagesNum();

    Integer getRecordsPerPage();

    void setRecordsPerPage(Integer recordsPerPage);

    Long getInsertPageNum();
}
