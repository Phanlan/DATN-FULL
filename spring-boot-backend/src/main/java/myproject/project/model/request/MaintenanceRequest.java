package myproject.project.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MaintenanceRequest extends ServiceRequest{
    @NotNull
    private Long period;
}
