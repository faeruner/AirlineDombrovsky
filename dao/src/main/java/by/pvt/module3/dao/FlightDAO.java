package by.pvt.module3.dao;

import by.pvt.module3.dao.common.BaseDAO;
import by.pvt.module3.entity.Flight;

public class FlightDAO extends BaseDAO<Flight> {
    public FlightDAO() {
        super(Flight.class);
    }
}
