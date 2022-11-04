package myproject.project.service;

import myproject.project.model.request.CleanedRequest;
import myproject.project.model.response.CleanedResponse;

public interface CleanedServiceBusinessService {
    CleanedResponse getActiveCleanedService();
    CleanedResponse createNewCleanedService(CleanedRequest cleanedRequest);
}
