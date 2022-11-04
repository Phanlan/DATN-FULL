package myproject.project.service;

import myproject.project.model.request.MonthRequest;
import myproject.project.model.response.BuildingEmployeeResponse;

import java.util.List;

public interface MonthSalaryService {
    List<BuildingEmployeeResponse> getAllMonthSalaryPrecent(boolean isDeleted);

    List<BuildingEmployeeResponse> getMonthSalaryByMonth(MonthRequest request);
}
