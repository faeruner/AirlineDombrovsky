package by.pvt.module3.service;

import by.pvt.module3.entity.Flight;
import by.pvt.module3.service.common.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by v on 08.09.2016.
 */
@Service
@Transactional
public class FlightService extends BaseService<Flight> {

//    public FlightService() {
//        super(new FlightDAO());
//    }
}
