package myproject.project.mapper;

import myproject.project.entity.ParkingService;
import myproject.project.model.request.ParkingServiceRequest;
import myproject.project.model.response.ParkingServiceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ParkingServiceMapper implements Mapper<ParkingServiceMapper>{

    public ParkingService to(ParkingServiceRequest request) {
        ParkingService parkingService = new ParkingService();
        BeanUtils.copyProperties(request,parkingService);
        return parkingService;
    }

    public ParkingServiceResponse to(ParkingService parkingService) {
        ParkingServiceResponse parkingServiceResponse = new ParkingServiceResponse();
        BeanUtils.copyProperties(parkingService, parkingServiceResponse);
        return parkingServiceResponse;
    }
}
