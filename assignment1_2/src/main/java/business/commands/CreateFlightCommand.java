package business.commands;

import dataAccess.dao.FlightDAO;
import dataAccess.model.Flight;

public class CreateFlightCommand implements Command {
    private Flight flight;

    public CreateFlightCommand(Flight flight) {
        this.flight = flight;
    }

    public Object execute() {
        FlightDAO dao = new FlightDAO();
        Flight flight1 = dao.createFlight(flight);
        if (flight1.getId() != -1)
            return flight1;

        return null;
    }
}
