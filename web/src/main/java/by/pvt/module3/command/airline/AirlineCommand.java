package by.pvt.module3.command.airline;

import by.pvt.module3.command.BaseCommand;
import by.pvt.module3.entity.Airline;
import by.pvt.module3.service.AirlineService;

/**
 * Created by v on 08.09.2016.
 */
public class AirlineCommand extends BaseCommand<Airline> {

    public AirlineCommand() {
        super(new AirlineService(), "path.page.edit_airline", "path.page.airlines");
    }
}
