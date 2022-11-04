package myproject.project.model.response;

import lombok.Data;

@Data
public class VehicleResponse {
    private Long id;
    private Long companyId;
    private String companyName;
    private Long companyEmployeeId;
    private String companyEmployeeName;
    private Long vehicleTypeId;
    private String vehicleTypeDescription;
    private String licensePlate;
}
