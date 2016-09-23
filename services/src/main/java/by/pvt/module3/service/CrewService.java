package by.pvt.module3.service;

import by.pvt.module3.dao.CrewDAO;
import by.pvt.module3.dao.common.CommonDAO;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.service.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by v on 08.09.2016.
 */
@Service
@Transactional
public class CrewService extends BaseService<Crew> {

//    public CrewService() {
//        super(new CrewDAO());
//    }

    @Autowired
    CrewDAO dao;

    @Override
    protected CommonDAO<Crew> getDao() {
        return dao;
    }

    public Crew getById(Integer id) {
        return getById(Crew.class, id);
    }

    public List<Crew> getAll() {
        return getAll(Crew.class);
    }
}
