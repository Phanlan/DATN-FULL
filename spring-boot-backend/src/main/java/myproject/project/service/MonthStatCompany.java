package myproject.project.service;

import myproject.project.model.response.MonthStatCompanyResponse;

import java.util.List;

public interface MonthStatCompany {
    List<MonthStatCompanyResponse> viewStatistic(int month, int year);
}
