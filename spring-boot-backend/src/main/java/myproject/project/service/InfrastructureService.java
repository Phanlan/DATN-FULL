package myproject.project.service;

import myproject.project.model.request.InfrastructureRequest;
import myproject.project.model.response.InfrastructureDetailResponse;
import myproject.project.model.response.InfrastructureResponse;

import java.util.List;

public interface InfrastructureService {
    InfrastructureResponse save(InfrastructureRequest request);

    InfrastructureDetailResponse findById(Long id);

    List<InfrastructureResponse> getAllInfrastructure();

    InfrastructureResponse updateById(Long id, InfrastructureRequest request);

    String deleteById(Long id);
}
