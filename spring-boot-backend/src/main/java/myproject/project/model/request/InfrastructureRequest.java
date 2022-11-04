package myproject.project.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InfrastructureRequest {
    @NotNull
    private String name;
//    private String placeOfUse;
    private String type;
//    private String status;
    private Integer quantity;
    private Float unitAmount;

}
