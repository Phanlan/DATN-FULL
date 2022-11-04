package myproject.project.service;

import myproject.project.model.request.VehicleRequest;
import myproject.project.model.response.VehicleResponse;

import java.util.List;

public interface VehicleService {
    VehicleResponse save(VehicleRequest request);

    List<VehicleResponse> getAllVehicle();
    VehicleResponse updateById(Long id, VehicleRequest request);
}
