package myproject.project.model.response;

import lombok.Data;

import java.util.List;

@Data
public class CompanyDetailResponse {
    private Long id;
    private String name;
    private String taxCode;
    private Float authorizedCapital;
    private String fieldOfActivity;
    private String floor;
    private String hotline;
    private Float area;
    private Float totalPrice;
    List<CompanyEmployeeResponse> companyEmployeeList;
    List<ServiceResponse> serviceList;
    List<UsedElectricWaterResponse> usedElectricWaterList;
    List<InfrastructureResponse> infrastructureList;
    List<VehicleResponse> vehicleResponseList;
}
