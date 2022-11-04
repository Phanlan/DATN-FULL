package myproject.project.model.response;

import lombok.Data;

@Data
public class UsedInfrastructureResponse {
    private Long id;

    private Integer quantity;

    private Long companyId;

    private Long infrastructureId;
}
