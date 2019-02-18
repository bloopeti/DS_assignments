package business.commands;

import dataAccess.dao.FlightDAO;
import dataAccess.model.Flight;

public class DeleteFlightCommand implements Command {
    private Flight flight;

    public DeleteFlightCommand(Flight flight) {
        this.flight = flight;
    }

    public Object execute() {
        FlightDAO dao = new FlightDAO();
        dao.deleteFlight(flight.getId());
        return null;
    }
}
