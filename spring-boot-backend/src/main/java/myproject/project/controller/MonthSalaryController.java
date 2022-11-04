package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.MonthRequest;
import myproject.project.model.response.BaseResponse;
import myproject.project.model.response.BuildingEmployeeResponse;
import myproject.project.service.MonthSalaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("public-api/v1.0.0/month-salary")
@AllArgsConstructor
public class MonthSalaryController {

    private final MonthSalaryService monthSalaryService;

    @GetMapping()
    BaseResponse<List<BuildingEmployeeResponse>> getAllMonthSalaryPrecent() {
        return BaseResponse.ofSuccess(monthSalaryService.getAllMonthSalaryPrecent(false));
    }

    @GetMapping("/{month}/{year}")
    BaseResponse<List<BuildingEmployeeResponse>> getMonthSalaryByMonth(@PathVariable int month, @PathVariable int year) {
        MonthRequest request = new MonthRequest();
        request.setMonth(month);
        request.setYear(year);
        return BaseResponse.ofSuccess(monthSalaryService.getMonthSalaryByMonth(request));
    }
}
