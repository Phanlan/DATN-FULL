package myproject.project.service;

import myproject.project.model.request.BuildingEmployeeRequest;
import myproject.project.model.response.BuildingEmployeeResponse;

import java.util.List;

public interface BuildingEmployeeService {

    BuildingEmployeeResponse save(BuildingEmployeeRequest buildingEmployeeRequest);
    BuildingEmployeeResponse save(Long id, BuildingEmployeeRequest request);
    BuildingEmployeeResponse findById(Long id);
    String deleteById(Long id);
    List<BuildingEmployeeResponse> getAllBuildingEmployee();
    List<BuildingEmployeeResponse> getBuildingEmployeeByName(String name);
}
