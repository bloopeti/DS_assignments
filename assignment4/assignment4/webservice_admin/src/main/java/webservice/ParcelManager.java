package webservice;

import model.RouteEntry;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "ParcelManager", serviceName = "ParcelManagerService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ParcelManager {
    @WebMethod
    void addParcel(String parcelName, String parcelDescription,
                   int parcelReceiverId, int parcelSenderId,
                   int parcelReceiverCityId, int parcelSenderCityId);

    @WebMethod
    void removeParcel(int parcelId);

    @WebMethod
    void startTrackingParcel(int parcelId);

    @WebMethod
    void updateParcelRoute(int parcelId, String time, int cityId);

    @WebMethod
    String serviceTest(String s);
}
