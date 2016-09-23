package by.pvt.module3.service;

import by.pvt.module3.entity.Airport;
import by.pvt.module3.service.common.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by v on 06.09.2016.
 */
@Service
@Transactional
public class AirportService extends BaseService<Airport> {

    //    public AirportService() {
//        super(new AirportDAO());
//    }
    public Airport getById(Integer id) {
        return getById(Airport.class, id);
    }

    public List<Airport> getAll() {
        return getAll(Airport.class);
    }
}
