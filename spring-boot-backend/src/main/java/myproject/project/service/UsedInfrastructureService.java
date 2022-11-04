package myproject.project.service;

import myproject.project.model.request.UsedInfrastructureRequest;
import myproject.project.model.response.UsedInfrastructureResponse;

public interface UsedInfrastructureService {
    UsedInfrastructureResponse save(UsedInfrastructureRequest request);
    String deleteByCompanyIdAndInfrastructureId(Long companyId, Long infrastructureId);
    UsedInfrastructureResponse updateById(Long companyId, Long infrastructureId, UsedInfrastructureRequest request);
}
