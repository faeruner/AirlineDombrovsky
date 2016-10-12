package by.pvt.module3.webservice;


import by.pvt.module3.entity.Airline;
import by.pvt.module3.service.common.CommonService;
import by.pvt.module3.webservice.schema.AirlineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class AirlinesRepository {

    @Autowired
    CommonService<Airline> airlineService;

    public AirlineType findAirline(Integer id) {
        Assert.notNull(id);
        return AirlinesMapping.getTypeMapped(airlineService.getById(id));
    }

    public List<AirlineType> getAirlines(Integer page, Integer size) {
        final List<AirlineType> result = new ArrayList<AirlineType>();
        airlineService.getPage(page, size).forEach(item -> result.add(AirlinesMapping.getTypeMapped(item)));
        return result;
    }


}
