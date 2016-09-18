package by.pvt.module3.service;

import by.pvt.module3.dao.FlightDAO;
import by.pvt.module3.entity.Flight;
import by.pvt.module3.service.common.BaseService;

/**
 * Created by v on 08.09.2016.
 */
public class FlightService extends BaseService<Flight> {

    public FlightService() {
        super(new FlightDAO());
    }
}
