package by.pvt.module3.command.client;

import by.pvt.module3.command.ActionCommand;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public enum CommandEnum {
    LOGIN {
        {
            this.command = context.getBean(LoginCommand.class);
        }
    },
    LOGOUT {
        {
            this.command = context.getBean(LogoutCommand.class);
        }
    },
    SEL_AIRPORT {
        {
            this.command = context.getBean(AirportCommand.class);
        }
    },
    INS_AIRPORT {
        {
            this.command = context.getBean(InsertAirportCommand.class);
        }
    },
    UPD_AIRPORT {
        {
            this.command = context.getBean(UpdateAirportCommand.class);
        }
    },
    DEL_AIRPORT {
        {
            this.command = context.getBean(DeleteAirportCommand.class);
        }
    },
    SEL_AIRLINE {
        {
            this.command = context.getBean(AirlineCommand.class);
        }
    },
    INS_AIRLINE {
        {
            this.command = context.getBean(InsertAirlineCommand.class);
        }
    },
    UPD_AIRLINE {
        {
            this.command = context.getBean(UpdateAirlineCommand.class);
        }
    },
    DEL_AIRLINE {
        {
            this.command = context.getBean(DeleteAirlineCommand.class);
        }
    },
    SEL_USER {
        {
            this.command = context.getBean(UserCommand.class);
        }
    },
    INS_USER {
        {
            this.command = context.getBean(InsertUserCommand.class);
        }
    },
    UPD_USER {
        {
            this.command = context.getBean(UpdateUserCommand.class);
        }
    },
    DEL_USER {
        {
            this.command = context.getBean(DeleteUserCommand.class);
        }
    },
    SEL_STAFF {
        {
            this.command = context.getBean(StaffCommand.class);
        }
    },
    INS_STAFF {
        {
            this.command = context.getBean(InsertStaffCommand.class);
        }
    },
    UPD_STAFF {
        {
            this.command = context.getBean(UpdateStaffCommand.class);
        }
    },
    DEL_STAFF {
        {
            this.command = context.getBean(DeleteStaffCommand.class);
        }
    },
    SEL_FLIGHT {
        {
            this.command = context.getBean(FlightCommand.class);
        }
    },
    INS_FLIGHT {
        {
            this.command = context.getBean(InsertFlightCommand.class);
        }
    },
    UPD_FLIGHT {
        {
            this.command = context.getBean(UpdateFlightCommand.class);
        }
    },
    DEL_FLIGHT {
        {
            this.command = context.getBean(DeleteFlightCommand.class);
        }
    },
    SEL_CREW {
        {
            this.command = context.getBean(CrewCommand.class);
        }
    },
    INS_CREW {
        {
            this.command = context.getBean(InsertCrewCommand.class);
        }
    },
    UPD_CREW {
        {
            this.command = context.getBean(UpdateCrewCommand.class);
        }
    },
    DEL_CREW {
        {
            this.command = context.getBean(DeleteCrewCommand.class);
        }
    },
    INS_MEMBER {
        {
            this.command = context.getBean(InsertMemberCommand.class);
        }
    },
    DEL_MEMBER {
        {
            this.command = context.getBean(DeleteMemberCommand.class);
        }
    };
    ActionCommand command;
    ApplicationContext context;

    CommandEnum() {
        context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}