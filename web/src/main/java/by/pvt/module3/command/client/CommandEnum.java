package by.pvt.module3.command.client;

import by.pvt.module3.command.airport.*;
import by.pvt.module3.command.crew.*;
import by.pvt.module3.command.flight.*;
import by.pvt.module3.command.crew.DeleteMemberCommand;
import by.pvt.module3.command.crew.InsertMemberCommand;
import by.pvt.module3.command.staff.*;
import by.pvt.module3.command.user.*;
import by.pvt.module3.command.ActionCommand;
import by.pvt.module3.command.AdminCommand;
import by.pvt.module3.command.DispCommand;
import by.pvt.module3.command.LoginCommand;
import by.pvt.module3.command.LogoutCommand;
import by.pvt.module3.command.airline.*;
import by.pvt.module3.command.airline.DeleteAirlineCommand;
import by.pvt.module3.command.airline.InsertAirlineCommand;
import by.pvt.module3.command.airline.UpdateAirlineCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    MAIN {
        {
            this.command = new AdminCommand();
        }
    },
    USER {
        {
            this.command = new DispCommand();
        }
    },
    SEL_AIRPORT {
        {
            this.command = new AirportCommand();
        }
    },
    INS_AIRPORT {
        {
            this.command = new InsertAirportCommand();
        }
    },
    UPD_AIRPORT {
        {
            this.command = new UpdateAirportCommand();
        }
    },
    DEL_AIRPORT {
        {
            this.command = new DeleteAirportCommand();
        }
    },
    SEL_AIRLINE {
        {
            this.command = new AirlineCommand();
        }
    },
    INS_AIRLINE {
        {
            this.command = new InsertAirlineCommand();
        }
    },
    UPD_AIRLINE {
        {
            this.command = new UpdateAirlineCommand();
        }
    },
    DEL_AIRLINE {
        {
            this.command = new DeleteAirlineCommand();
        }
    },
    SEL_USER {
        {
            this.command = new UserCommand();
        }
    },
    INS_USER {
        {
            this.command = new InsertUserCommand();
        }
    },
    UPD_USER {
        {
            this.command = new UpdateUserCommand();
        }
    },
    DEL_USER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    SEL_STAFF {
        {
            this.command = new StaffCommand();
        }
    },
    INS_STAFF {
        {
            this.command = new InsertStaffCommand();
        }
    },
    UPD_STAFF {
        {
            this.command = new UpdateStaffCommand();
        }
    },
    DEL_STAFF {
        {
            this.command = new DeleteStaffCommand();
        }
    },
    SEL_FLIGHT {
        {
            this.command = new FlightCommand();
        }
    },
    INS_FLIGHT {
        {
            this.command = new InsertFlightCommand();
        }
    },
    UPD_FLIGHT {
        {
            this.command = new UpdateFlightCommand();
        }
    },
    DEL_FLIGHT {
        {
            this.command = new DeleteFlightCommand();
        }
    },
    SEL_CREW {
        {
            this.command = new CrewCommand();
        }
    },
    INS_CREW {
        {
            this.command = new InsertCrewCommand();
        }
    },
    UPD_CREW {
        {
            this.command = new UpdateCrewCommand();
        }
    },
    DEL_CREW {
        {
            this.command = new DeleteCrewCommand();
        }
    },
    INS_MEMBER {
        {
            this.command = new InsertMemberCommand();
        }
    },
    DEL_MEMBER{
        {
            this.command = new DeleteMemberCommand();
        }
    };
ActionCommand command;

public ActionCommand getCurrentCommand() {
    return command;
}
}