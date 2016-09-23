package by.pvt.module3.service;

import by.pvt.module3.entity.Airline;
import by.pvt.module3.service.common.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by v on 07.09.2016.
 */
@Service
@Transactional
public class AirlineService extends BaseService<Airline> {

    //    public AirlineService() {
//        super(new AirlineDAO());
//    }
    public Airline getById(Integer id) {
        return getById(Airline.class, id);
    }

    public List<Airline> getAll() {
        return getAll(Airline.class);
    }
}
