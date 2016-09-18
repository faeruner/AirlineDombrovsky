package by.pvt.module3.service;

import by.pvt.module3.dao.AirlineDAO;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.service.common.BaseService;

/**
 * Created by v on 07.09.2016.
 */
public class AirlineService extends BaseService<Airline> {

    public AirlineService() {
        super(new AirlineDAO());
    }
}
