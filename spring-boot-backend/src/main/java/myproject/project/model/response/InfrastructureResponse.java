package myproject.project.model.response;

import lombok.Data;

@Data
public class InfrastructureResponse {
    private Long id;
    private String name;
//    private String placeOfUse;
    private String type;
//    private String status;
    private Integer quantity;
    private Integer quantityCompanyUse;
    private Float unitAmount;
}
