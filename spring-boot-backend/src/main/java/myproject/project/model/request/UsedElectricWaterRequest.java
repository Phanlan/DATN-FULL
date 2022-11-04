package myproject.project.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UsedElectricWaterRequest {
    private Long waterNumber;
    private Long electricNumber;
    private String month;
    @NotNull
    private Long company_id;
}
