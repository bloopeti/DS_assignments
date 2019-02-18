package webservice;

import javax.xml.ws.Endpoint;

public class WebservicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9901/adminService", new AdminParcelManager());
    }
}
