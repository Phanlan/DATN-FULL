package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.VehicleRequest;
import myproject.project.model.response.BaseResponse;
import myproject.project.model.response.VehicleResponse;
import myproject.project.model.response.VehicleTypeResponse;
import myproject.project.service.VehicleService;
import myproject.project.service.VehicleTypeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("public-api/v1.0.0/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    private final VehicleTypeService vehicleTypeService;

    @PostMapping("/create")
    BaseResponse<VehicleResponse> createVehicle(@RequestBody @Valid VehicleRequest request){
        return BaseResponse.ofSuccess(vehicleService.save(request));
    }

    @GetMapping("/list")
    BaseResponse<List<VehicleResponse>> getAllVehicle(){
        return BaseResponse.ofSuccess(vehicleService.getAllVehicle());
    }

    @PutMapping("/update/{id}")
    BaseResponse<VehicleResponse> updateVehicle(@PathVariable Long id, @RequestBody @Valid VehicleRequest request){
        return BaseResponse.ofSuccess(vehicleService.updateById(id,request));
    }

    @GetMapping("/list-vehicleType")
    BaseResponse<List<VehicleTypeResponse>> getAllVehicleType(){
        return BaseResponse.ofSuccess(vehicleTypeService.getAllVehicleType());
    }
}
