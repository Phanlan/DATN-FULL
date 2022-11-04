package myproject.project.service;

import myproject.project.model.request.ProtectedServiceRequest;
import myproject.project.model.response.ProtectedServiceResponse;

public interface ProtectedServiceBusinessService {
    ProtectedServiceResponse getActiveProtectedService();
    ProtectedServiceResponse createNewProtectedService(ProtectedServiceRequest request);
}
