package by.pvt.module3.dao;

import by.pvt.module3.dao.common.BaseDAO;
import by.pvt.module3.entity.Airport;

public class AirportDAO extends BaseDAO<Airport> {
    public AirportDAO() {
        super(Airport.class);
    }
}
