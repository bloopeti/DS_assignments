package business.commands;

import dataAccess.dao.FlightDAO;
import dataAccess.model.Flight;

public class UpdateFlightCommand implements Command {
    private Flight flight;

    public UpdateFlightCommand(Flight flight) {
        this.flight = flight;
    }

    public Object execute() {
        FlightDAO dao = new FlightDAO();
        dao.updateFlight(flight);
        return null;
    }
}
