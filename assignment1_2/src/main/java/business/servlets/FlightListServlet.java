package business.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FlightListServlet", urlPatterns = {"/flightlist"})
public class FlightListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String usertype = null;
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("usertype"))
                usertype = cookie.getValue();
        }
        response.sendRedirect("/" + usertype);
    }
}
