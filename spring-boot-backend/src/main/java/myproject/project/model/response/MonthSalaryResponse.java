package myproject.project.model.response;

import lombok.Data;

import java.util.List;

@Data
public class MonthSalaryResponse {
    private List<BuildingEmployeeResponse> buildingEmployeeResponse;
}
