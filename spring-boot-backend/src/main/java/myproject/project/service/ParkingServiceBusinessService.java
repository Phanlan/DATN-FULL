package myproject.project.service;

import myproject.project.model.request.ParkingServiceRequest;
import myproject.project.model.response.ParkingServiceResponse;

public interface ParkingServiceBusinessService {
    ParkingServiceResponse getActiveParkingService();
    ParkingServiceResponse createNewParkingService(ParkingServiceRequest request);
}
