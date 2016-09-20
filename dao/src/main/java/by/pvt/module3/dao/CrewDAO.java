package by.pvt.module3.dao;

import by.pvt.module3.dao.common.BaseDAO;
import by.pvt.module3.entity.Crew;

public class CrewDAO extends BaseDAO<Crew> {
    public CrewDAO() {
        super(Crew.class);
    }

    @Override
    protected void prepareDelete(Crew t) {
        t.getMembers().clear();
    }
}
