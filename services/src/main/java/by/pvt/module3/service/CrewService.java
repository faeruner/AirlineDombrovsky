package by.pvt.module3.service;

import by.pvt.module3.dao.CrewDAO;
import by.pvt.module3.entity.Crew;
import by.pvt.module3.service.common.BaseService;

/**
 * Created by v on 08.09.2016.
 */
public class CrewService extends BaseService<Crew> {

    public CrewService() {
        super(new CrewDAO());
    }
}
