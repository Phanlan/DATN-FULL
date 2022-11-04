package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.MaintenanceRequest;
import myproject.project.service.MaintenanceBusinessService;
import myproject.project.model.response.BaseResponse;
import myproject.project.model.response.MaintenanceResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("public-api/v1.0.0/maintenance-service")
@AllArgsConstructor
public class MaintenanceServiceController {

    private final MaintenanceBusinessService maintenanceBusinessService;

    @GetMapping()
    public BaseResponse<MaintenanceResponse> getCurrentMaintenanceService(){
        return BaseResponse.ofSuccess(maintenanceBusinessService.getActiveMaintenanceService());
    }

    @PostMapping()
    public BaseResponse<MaintenanceResponse> createNewMaintenanceService(@RequestBody @Valid MaintenanceRequest maintenanceRequest){
        return BaseResponse.ofSuccess(maintenanceBusinessService.createNewMaintenanceService(maintenanceRequest));
    }
}
