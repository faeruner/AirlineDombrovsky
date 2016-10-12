package by.pvt.module3.webservice.common;

import by.pvt.module3.entity.Fact;
import by.pvt.module3.webservice.schema.FactType;

import java.util.List;

public interface CommonRepository<T extends FactType, F extends Fact> {
    T getById(Integer id);

    void delete(Integer id);

    Integer add(T model);

    T update(T model);

    List<T> getPage(Integer page, Integer size);

    List<Integer> getPageNumbers(Integer size);

    Long getInsertPageNum(Integer size);
}
