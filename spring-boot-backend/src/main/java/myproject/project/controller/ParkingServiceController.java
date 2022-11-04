package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.ParkingServiceRequest;
import myproject.project.model.response.BaseResponse;
import myproject.project.model.response.ParkingServiceResponse;
import myproject.project.service.ParkingServiceBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("public-api/v1.0.0/parking-service")
@AllArgsConstructor
public class ParkingServiceController {

    private final ParkingServiceBusinessService parkingService;

    @GetMapping()
    public BaseResponse<ParkingServiceResponse> getCurrentParkingService(){
        return BaseResponse.ofSuccess(parkingService.getActiveParkingService());
    }

    @PostMapping()
    public BaseResponse<ParkingServiceResponse> createNewParkingService(@RequestBody @Valid ParkingServiceRequest request){
        return BaseResponse.ofSuccess(parkingService.createNewParkingService(request));
    }

}
