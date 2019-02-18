package business.commands;

import dataAccess.dao.FlightDAO;
import dataAccess.model.Flight;

import java.util.List;

public class ReadAllFlightsCommand implements Command {
    public ReadAllFlightsCommand() {
    }

    public Object execute() {
        FlightDAO dao = new FlightDAO();
        List<Flight> flights = dao.readAllFlights();
        if (flights != null && !flights.isEmpty())
            return flights;

        return null;
    }
}
