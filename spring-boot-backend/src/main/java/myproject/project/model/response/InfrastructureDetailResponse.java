package myproject.project.model.response;

import lombok.Data;

import java.util.List;

@Data
public class InfrastructureDetailResponse {
    private Long id;
    private String name;
    private String type;
    private Integer quantity;
    private Float unitAmount;
    List<CompanyResponse> companyList;
}
