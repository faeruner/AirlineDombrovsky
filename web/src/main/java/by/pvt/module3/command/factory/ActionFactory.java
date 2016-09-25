package by.pvt.module3.command.factory;

import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.EmptyCommand;
import by.pvt.module3.command.LoginCommand;
import by.pvt.module3.command.LogoutCommand;
import by.pvt.module3.command.airline.AirlineCommand;
import by.pvt.module3.command.airline.DeleteAirlineCommand;
import by.pvt.module3.command.airline.InsertAirlineCommand;
import by.pvt.module3.command.airline.UpdateAirlineCommand;
import by.pvt.module3.command.airport.AirportCommand;
import by.pvt.module3.command.airport.DeleteAirportCommand;
import by.pvt.module3.command.airport.InsertAirportCommand;
import by.pvt.module3.command.airport.UpdateAirportCommand;
import by.pvt.module3.command.client.CommandEnum;
import by.pvt.module3.command.crew.*;
import by.pvt.module3.command.flight.DeleteFlightCommand;
import by.pvt.module3.command.flight.FlightCommand;
import by.pvt.module3.command.flight.InsertFlightCommand;
import by.pvt.module3.command.flight.UpdateFlightCommand;
import by.pvt.module3.command.staff.DeleteStaffCommand;
import by.pvt.module3.command.staff.InsertStaffCommand;
import by.pvt.module3.command.staff.StaffCommand;
import by.pvt.module3.command.staff.UpdateStaffCommand;
import by.pvt.module3.command.user.DeleteUserCommand;
import by.pvt.module3.command.user.InsertUserCommand;
import by.pvt.module3.command.user.UpdateUserCommand;
import by.pvt.module3.command.user.UserCommand;
import by.pvt.module3.resource.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Map;

@Component
public class ActionFactory {
    public static final String PARAM_COMMAND = "command";

    @Autowired
    EmptyCommand emptyCommand;

    @Autowired
    LoginCommand loginCommand;
    @Autowired
    LogoutCommand logoutCommand;
    @Autowired
    AirportCommand airportCommand;
    @Autowired
    InsertAirportCommand insertAirportCommand;
    @Autowired
    UpdateAirportCommand updateAirpordCommand;
    @Autowired
    DeleteAirportCommand deleteAirpordCommand;
    @Autowired
    AirlineCommand airlineCommand;
    @Autowired
    InsertAirlineCommand insertAirlineCommand;
    @Autowired
    UpdateAirlineCommand updateAirlineCommand;
    @Autowired
    DeleteAirlineCommand deleteAirlineCommand;
    @Autowired
    UserCommand userCommand;
    @Autowired
    InsertUserCommand insertUserCommand;
    @Autowired
    UpdateUserCommand updateUserCommand;
    @Autowired
    DeleteUserCommand deleteUserCommand;
    @Autowired
    StaffCommand staffCommand;
    @Autowired
    InsertStaffCommand insertStaffCommand;
    @Autowired
    UpdateStaffCommand updateStaffCommand;
    @Autowired
    DeleteStaffCommand deleteStaffCommand;
    @Autowired
    FlightCommand flightCommand;
    @Autowired
    InsertFlightCommand insertFlightCommand;
    @Autowired
    UpdateFlightCommand updateFlightCommand;
    @Autowired
    DeleteFlightCommand deleteFlightCommand;
    @Autowired
    CrewCommand crewCommand;
    @Autowired
    InsertCrewCommand insertCrewCommand;
    @Autowired
    UpdateCrewCommand updateCrewCommand;
    @Autowired
    DeleteCrewCommand deleteCrewCommand;
    @Autowired
    InsertMemberCommand insertMemberCommand;
    @Autowired
    DeleteMemberCommand deleteMemberCommand;

    public ActionCommand defineCommand(Map<String, String> paramMap, Model model) {
        ActionCommand current = emptyCommand;
        String action = paramMap.get(PARAM_COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            switch (currentEnum) {
                case LOGIN:
                    current = loginCommand;
                    break;
                case LOGOUT:
                    current = logoutCommand;
                    break;
                case SEL_AIRPORT:
                    current = airportCommand;
                    break;
                case INS_AIRPORT:
                    current = insertAirportCommand;
                    break;
                case UPD_AIRPORT:
                    current = updateAirpordCommand;
                    break;
                case DEL_AIRPORT:
                    current = deleteAirpordCommand;
                    break;
                case SEL_AIRLINE:
                    current = airlineCommand;
                    break;
                case INS_AIRLINE:
                    current = insertAirlineCommand;
                    break;
                case UPD_AIRLINE:
                    current = updateAirlineCommand;
                    break;
                case DEL_AIRLINE:
                    current = deleteAirlineCommand;
                    break;
                case SEL_USER:
                    current = userCommand;
                    break;
                case INS_USER:
                    current = insertUserCommand;
                    break;
                case UPD_USER:
                    current = updateUserCommand;
                    break;
                case DEL_USER:
                    current = deleteUserCommand;
                    break;
                case SEL_STAFF:
                    current = staffCommand;
                    break;
                case INS_STAFF:
                    current = insertStaffCommand;
                    break;
                case UPD_STAFF:
                    current = updateStaffCommand;
                    break;
                case DEL_STAFF:
                    current = deleteStaffCommand;
                    break;
                case SEL_FLIGHT:
                    current = flightCommand;
                    break;
                case INS_FLIGHT:
                    current = insertFlightCommand;
                    break;
                case UPD_FLIGHT:
                    current = updateFlightCommand;
                    break;
                case DEL_FLIGHT:
                    current = deleteFlightCommand;
                    break;
                case SEL_CREW:
                    current = crewCommand;
                    break;
                case INS_CREW:
                    current = insertCrewCommand;
                    break;
                case UPD_CREW:
                    current = updateCrewCommand;
                    break;
                case DEL_CREW:
                    current = deleteCrewCommand;
                    break;
                case INS_MEMBER:
                    current = insertMemberCommand;
                    break;
                case DEL_MEMBER:
                    current = deleteMemberCommand;
                    break;
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}