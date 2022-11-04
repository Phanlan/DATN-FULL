package myproject.project.service;

import myproject.project.model.request.FoodServiceRequest;
import myproject.project.model.response.FoodServiceResponse;

public interface FoodServiceBusinessService {
    FoodServiceResponse getActiveFoodService();
    FoodServiceResponse createNewFoodService(FoodServiceRequest request);
}
