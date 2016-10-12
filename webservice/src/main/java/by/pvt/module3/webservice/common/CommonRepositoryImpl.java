package by.pvt.module3.webservice.common;

import by.pvt.module3.entity.Fact;
import by.pvt.module3.service.common.CommonService;
import by.pvt.module3.webservice.AirlinesMapping;
import by.pvt.module3.webservice.schema.FactType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SBT-Dombrovsky-AE on 12.10.2016.
 */
@Repository
public class CommonRepositoryImpl<T extends FactType, F extends Fact> implements CommonRepository<T, F> {

    private final CommonService<F> service;
    private AirlinesMapping<T, F> mapping;

    @Autowired
    public CommonRepositoryImpl(CommonService<F> service) {
        Assert.notNull(service);
        this.service = service;
        mapping = new AirlinesMapping<>();
    }

    @Override
    public T getById(Integer id) {
        return mapping.getTypeMapped(service.getById(id));
    }

    @Override
    public void delete(Integer id) {
        service.delete(id);
    }

    @Override
    public Integer add(T model) {
        return service.add(mapping.getTypeMapped(model));
    }

    @Override
    public T update(T model) {
        return mapping.getTypeMapped(service.update(mapping.getTypeMapped(model)));
    }

    @Override
    public List<T> getPage(Integer page, Integer size) {
        final List<T> result = new ArrayList<T>();
        service.getPage(page, size).forEach(item -> result.add(mapping.getTypeMapped(item)));
        return result;
    }

    @Override
    public List<Integer> getPageNumbers(Integer size) {
        return service.getPageNumbers(size);
    }

    @Override
    public Long getInsertPageNum(Integer size) {
        return service.getInsertPageNum(size);
    }
}
