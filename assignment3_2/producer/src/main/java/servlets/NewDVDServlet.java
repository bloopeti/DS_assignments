package servlets;

import messaging.DVDSender;
import model.DVD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewDVDServlet", urlPatterns = {"/newDVD"})
public class NewDVDServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String title = req.getParameter("title");
        String year = req.getParameter("year");
        String price = req.getParameter("price");
        DVD dvd = new DVD(title, Integer.parseInt(year), Double.parseDouble(price));

        DVDSender.sendNewDVD(dvd);

        resp.sendRedirect("/newDVD");
    }
}
