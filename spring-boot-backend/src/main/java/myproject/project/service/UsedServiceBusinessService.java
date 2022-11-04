package myproject.project.service;

import myproject.project.model.request.UsedServiceRequest;
import myproject.project.model.response.UsedServiceResponse;

import java.util.List;

public interface UsedServiceBusinessService {

    UsedServiceResponse save(UsedServiceRequest request);

    String deleteByCompanyIdAndServiceId(Long companyId, Long serviceId);

    List<UsedServiceResponse> findUsedServiceMonthByCompany(Long companyId, Long month);

}
