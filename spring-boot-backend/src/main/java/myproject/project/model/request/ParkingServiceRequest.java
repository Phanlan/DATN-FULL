package myproject.project.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ParkingServiceRequest extends ServiceRequest{
    @NotNull
    private Long slot;
}
