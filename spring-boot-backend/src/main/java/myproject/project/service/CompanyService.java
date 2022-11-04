package myproject.project.service;

import myproject.project.model.request.CompanyRequest;
import myproject.project.model.response.CompanyDetailResponse;
import myproject.project.model.response.CompanyResponse;

import java.util.List;

public interface CompanyService {
    CompanyResponse save(CompanyRequest request);

    CompanyResponse updateById(Long id, CompanyRequest request);

    CompanyDetailResponse findById(Long id);

    String deleteById(Long id);

    List<CompanyResponse> getAllCompany();

    List<CompanyResponse> findCompanyByNameLike(String name);
}
