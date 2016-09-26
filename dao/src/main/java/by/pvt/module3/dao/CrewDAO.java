package by.pvt.module3.dao;

import by.pvt.module3.dao.common.BaseDAO;
import by.pvt.module3.entity.Crew;
import org.springframework.stereotype.Repository;

@Repository
public class CrewDAO extends BaseDAO<Crew> {

    @Override
    protected void prepareDelete(Crew t) {
        t.getMembers().clear();
    }
}
