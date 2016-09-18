package by.pvt.module3.service;

import by.pvt.module3.dao.AirportDAO;
import by.pvt.module3.entity.Airport;
import by.pvt.module3.service.common.BaseService;

/**
 * Created by v on 06.09.2016.
 */
public class AirportService extends BaseService<Airport> {

    public AirportService() {
        super(new AirportDAO());
    }
}
