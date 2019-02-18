package servlets;

import endpoint.AdminParcelManagerService;
import endpoint.ParcelManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("admin.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if (operation.equals("create")) {
            String parcelName = req.getParameter("parcelName");
            String parcelDescription = req.getParameter("parcelDescription");
            int parcelSenderId = Integer.parseInt(req.getParameter("parcelSenderId"));
            int parcelReceiverId = Integer.parseInt(req.getParameter("parcelReceiverId"));
            int parcelSenderCityId = Integer.parseInt(req.getParameter("parcelSenderCityId"));
            int parcelReceiverCityId = Integer.parseInt(req.getParameter("parcelReceiverCityId"));


            ParcelManager service = new AdminParcelManagerService().getAdminParcelManagerPort();
            service.addParcel(parcelName, parcelDescription, parcelReceiverId, parcelSenderId, parcelReceiverCityId, parcelSenderCityId);

            resp.sendRedirect("/admin");
        } else if (operation.equals("delete")) {
            int parcelId = Integer.parseInt(req.getParameter("parcelId"));

            ParcelManager service = new AdminParcelManagerService().getAdminParcelManagerPort();
            service.removeParcel(parcelId);

            resp.sendRedirect("/admin");
        } else if (operation.equals("track")) {
            int parcelId = Integer.parseInt(req.getParameter("parcelId"));

            ParcelManager service = new AdminParcelManagerService().getAdminParcelManagerPort();
            service.startTrackingParcel(parcelId);

            resp.sendRedirect("/admin");
        } else if (operation.equals("updateRoute")) {
            int parcelId = Integer.parseInt(req.getParameter("parcelId"));
            String routeEntryTime = req.getParameter("routeEntryTime");
            int routeEntryCityId = Integer.parseInt(req.getParameter("routeEntryCityId"));

            ParcelManager service = new AdminParcelManagerService().getAdminParcelManagerPort();
            service.updateParcelRoute(parcelId, routeEntryTime, routeEntryCityId);

            resp.sendRedirect("/admin");
        }
    }
}
