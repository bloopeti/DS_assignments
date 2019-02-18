import dao.CityDAO;
import dao.ParcelDAO;
import dao.RouteEntryDAO;
import dao.UserDAO;
import model.Parcel;
import model.RouteEntry;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class TestStuff {

    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//
//        User user1 = new User();
//        user1.setUsername("dummy");
//        user1.setPass("dummy");
//        user1.setIsAdmin(0);
//        User user = userDAO.createUser(user1);

//        Parcel parcel = new Parcel();
//        parcel.setParcelDescription("dummy");
//        parcel.setParcelName("dummy");
//        parcel.setParcelReceiver(UserDAO.readUserById(2));
//        parcel.setParcelSender(UserDAO.readUserById(1));
//        parcel.setParcelReceiverCity(CityDAO.readCityById(1));
//        parcel.setParcelSenderCity(CityDAO.readCityById(2));
//        parcelDAO.createParcel(parcel);

        Parcel parcel = ParcelDAO.readParcelById(1);

        assert parcel != null;
        parcel.setIsTracked(1);
//        RouteEntry routeEntry = new RouteEntry();
//        routeEntry.setCity(CityDAO.readCityById(3));
//        routeEntry.setTime("123131313");
//        RouteEntryDAO.createRouteEntry(routeEntry);
//        List<RouteEntry> route = new ArrayList<RouteEntry>();
//        route.add(routeEntry);
//        parcel.setRoute(route);
        ParcelDAO.updateParcel(parcel);
    }
}
