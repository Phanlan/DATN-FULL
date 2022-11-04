package myproject.project.model.request;

import lombok.Data;

@Data
public class VehicleRequest {
    private Long companyId;
    private Long companyEmployeeId;
    private Long vehicleTypeId;
    private String licensePlate;
}
