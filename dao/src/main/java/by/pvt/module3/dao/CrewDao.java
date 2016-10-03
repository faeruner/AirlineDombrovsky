package by.pvt.module3.dao;

import by.pvt.module3.dao.common.BaseDao;
import by.pvt.module3.entity.Crew;
import org.springframework.stereotype.Repository;

@Repository
public class CrewDao extends BaseDao<Crew> {

    @Override
    protected void prepareDelete(Crew t) {
        t.getMembers().clear();
    }
}
