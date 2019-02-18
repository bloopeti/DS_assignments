package business.servlets;

import dataAccess.dao.FlightDAO;
import dataAccess.model.Flight;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "   <meta charset=\"UTF-8\">\n" +
                "   <title>Flight List User Page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Flight List User</h1>\n" +
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
        for (Flight flight : flights)
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user.html").forward(request, response);
    }
}
