package myproject.project.model.response;

import lombok.Data;

@Data
public class ParkingServiceResponse extends ServiceResponse{
    private Long slot;
}
