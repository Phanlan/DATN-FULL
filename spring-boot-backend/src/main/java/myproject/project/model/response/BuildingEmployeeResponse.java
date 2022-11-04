package myproject.project.model.response;

import lombok.Data;

@Data
public class BuildingEmployeeResponse {
    private Long id;
    private String code;
    private String name;
    private String dateOfBirth;
    private String address;
    private String phone;
    private SalaryResponse salaryResponse;
}
