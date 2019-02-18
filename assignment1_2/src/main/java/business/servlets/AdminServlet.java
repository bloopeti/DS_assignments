package business.servlets;

import dataAccess.dao.CityDAO;
import dataAccess.dao.FlightDAO;
import dataAccess.model.City;
import dataAccess.model.Flight;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("create")) {
            City departureCity = CityDAO.readCityById(Integer.parseInt(request.getParameter("departureCityId")));
            City arrivalCity = CityDAO.readCityById(Integer.parseInt(request.getParameter("arrivalCityId")));
            Flight flight = new Flight();
            flight.setFlightNr(Integer.parseInt(request.getParameter("flightNr")));
            flight.setAirplaneType(request.getParameter("airplaneType"));
            flight.setDepartureCity(departureCity);
            flight.setDepartureTime(request.getParameter("departureTime"));
            flight.setArrivalCity(arrivalCity);
            flight.setArrivalTime(request.getParameter("arrivalTime"));

            FlightDAO.createFlight(flight);

            response.sendRedirect("/admin");
        } else if (operation.equals("delete")) {
            int flightId = Integer.parseInt(request.getParameter("flightId"));
            FlightDAO.deleteFlight(flightId);

            response.sendRedirect("/admin");
        } else if (operation.equals("update")) {
            City departureCity = CityDAO.readCityById(Integer.parseInt(request.getParameter("departureCityId")));
            City arrivalCity = CityDAO.readCityById(Integer.parseInt(request.getParameter("arrivalCityId")));
            Flight flight = new Flight();
            flight.setId(Integer.parseInt(request.getParameter("flightId")));
            flight.setFlightNr(Integer.parseInt(request.getParameter("flightNr")));
            flight.setAirplaneType(request.getParameter("airplaneType"));
            flight.setDepartureCity(departureCity);
            flight.setDepartureTime(request.getParameter("departureTime"));
            flight.setArrivalCity(arrivalCity);
            flight.setArrivalTime(request.getParameter("arrivalTime"));

            FlightDAO.updateFlight(flight);

            response.sendRedirect("/admin");
        } else if (operation.equals("read")) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "   <meta charset=\"UTF-8\">\n" +
                            "   <title>Flight List Admin Page</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<h1>Flight List Admin</h1>\n" +
                            "<form action=\"/flightlist\" method=\"POST\">" +
                            "   <input type=\"submit\" value=\"Go back\"/>\n" +
                            "</form>\n" +
                            "<table style=\"width: 100%\" border=\"1px solid black\">\n" +
                            "    <tr>\n" +
                            "        <th>id</th>\n" +
                            "        <th>flightNr</th>\n" +
                            "        <th>airplaneType</th>\n" +
                            "        <th>departureCity</th>\n" +
                            "        <th>departureTime</th>\n" +
                            "        <th>arrivalCity</th>\n" +
                            "        <th>arrivalTime</th>\n" +
                            "    </tr>");

            List<Flight> flights = FlightDAO.readAllFlights();
            for(Flight flight:flights)
                out.println(
                        "   <tr>\n" +
                                "        <td>" + flight.getId() + "</td>\n" +
                                "        <td>" + flight.getFlightNr() + "</td>\n" +
                                "        <td>" + flight.getAirplaneType() + "</td>\n" +
                                "        <td>" + flight.getDepartureCity().getCityName() + "</td>\n" +
                                "        <td>" + flight.getDepartureTime() + "</td>\n" +
                                "        <td>" + flight.getArrivalCity().getCityName() + "</td>\n" +
                                "        <td>" + flight.getArrivalTime() + "</td>\n" +
                                "    </tr>");

            out.println(
                    "</table>\n" +
                            "</body>\n" +
                            "</html>"
            );
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String usertype = (String) session.getAttribute("usertype");
        if (!(usertype.equals("admin"))) {
            response.sendRedirect("/");
        } else
            request.getRequestDispatcher("admin.html").forward(request, response);
    }
}
