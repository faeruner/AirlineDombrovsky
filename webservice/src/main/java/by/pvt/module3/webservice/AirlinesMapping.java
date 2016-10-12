package by.pvt.module3.webservice;

import by.pvt.module3.entity.Airline;
import by.pvt.module3.entity.Fact;
import by.pvt.module3.webservice.schema.AirlineType;
import by.pvt.module3.webservice.schema.FactType;

import java.lang.reflect.ParameterizedType;

/**
 * Created by SBT-Dombrovsky-AE on 12.10.2016.
 */
public class AirlinesMapping<T extends FactType, F extends Fact> {

    private final Class<T> clazzF;

    public AirlinesMapping() {
        this.clazzF = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public T getTypeMapped(F model) {
        if (clazzF.equals(Airline.class)) {
            return (T) getTypeMapped((Airline) model);
        }
        return null;
    }

    public F getTypeMapped(T model) {
        if (clazzF.equals(Airline.class)) {
            return (F) getTypeMapped((AirlineType) model);
        }
        return null;
    }

    public static Airline getTypeMapped(AirlineType model) {
        return new Airline(model.getId(), model.getName());
    }

    public static AirlineType getTypeMapped(Airline model) {
        AirlineType res = new AirlineType();
        if (model != null) {
            res.setId(model.getId());
            res.setName(model.getName());
        }
        return res;
    }
}
