package myproject.project.model.response;

import lombok.Data;

@Data
public class MaintenanceResponse extends ServiceResponse {
    private Long period;
}
