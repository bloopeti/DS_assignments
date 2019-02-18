package webservice;

import dao.CityDAO;
import dao.ParcelDAO;
import dao.RouteEntryDAO;
import dao.UserDAO;
import model.City;
import model.Parcel;
import model.RouteEntry;

import javax.jws.WebService;

@WebService(endpointInterface = "webservice.ParcelManager")
public class AdminParcelManager implements ParcelManager {
    public AdminParcelManager() {
    }

    @Override
    public void addParcel(String parcelName, String parcelDescription,
                          int parcelReceiverId, int parcelSenderId,
                          int parcelReceiverCityId, int parcelSenderCityId) {
        Parcel parcel = new Parcel();
        parcel.setParcelDescription(parcelDescription);
        parcel.setParcelName(parcelName);
        parcel.setParcelReceiver(UserDAO.readUserById(parcelReceiverId));
        parcel.setParcelSender(UserDAO.readUserById(parcelSenderId));
        parcel.setParcelReceiverCity(CityDAO.readCityById(parcelReceiverCityId));
        parcel.setParcelSenderCity(CityDAO.readCityById(parcelSenderCityId));

        ParcelDAO.createParcel(parcel);
    }

    @Override
    public void removeParcel(int parcelId) {
        ParcelDAO.deleteParcel(parcelId);
    }

    @Override
    public void startTrackingParcel(int parcelId) {
        Parcel parcel = ParcelDAO.readParcelById(parcelId);
        assert parcel != null;
        if (parcel.getIsTracked() != 1) {
            parcel.setIsTracked(1);
            ParcelDAO.updateParcel(parcel);
        }
    }

    @Override
    public void updateParcelRoute(int parcelId, String time, int cityId) {
        Parcel parcel = ParcelDAO.readParcelById(parcelId);
        assert parcel != null;
        if (parcel.getIsTracked() == 1) {
            RouteEntry routeEntry = new RouteEntry();
            routeEntry.setTime(time);
            City city = CityDAO.readCityById(cityId);
            assert city != null;
            routeEntry.setCity(city);
            RouteEntryDAO.createRouteEntry(routeEntry);
            parcel.getRoute().add(routeEntry);
            ParcelDAO.updateParcel(parcel);
        }
    }

    @Override
    public String serviceTest(String s) {
        System.out.println("Param received: " + s);
        return "Service returns: " + s;
    }
}
