package myproject.project.service;

import myproject.project.model.request.SalaryRequest;
import myproject.project.model.response.SalaryResponse;

import java.util.List;

public interface SalaryService {

    SalaryResponse save(SalaryRequest salaryRequest);
    SalaryResponse save(Long id, SalaryRequest salaryRequest);
    SalaryResponse findById(Long id);
    String deleteById(Long id);
    List<SalaryResponse> getAllSalary();
    List<SalaryResponse> findSalaryByPositionLike(String position);
    List<String> findAllPosition();
    List<String> findLevelByPosition(String position);
}
