package myproject.project.model.response;

import lombok.Data;

@Data
public class UsedElectricWaterResponse {
    private Long waterNumber;
    private Long electricNumber;
    private String month;
    private Long company_id;
    private String companyName;

}
