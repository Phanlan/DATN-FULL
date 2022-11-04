package myproject.project.service;

import myproject.project.model.response.ServiceResponse;

import java.util.List;

public interface ServiceBusinessService {
    List<ServiceResponse> findAllService();
    List<ServiceResponse> findServiceNotUsedByCompanyId(Long companyId);
}
