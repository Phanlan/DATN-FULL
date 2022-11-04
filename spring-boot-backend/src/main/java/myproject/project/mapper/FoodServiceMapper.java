package myproject.project.mapper;

import myproject.project.model.request.FoodServiceRequest;
import myproject.project.entity.FoodService;
import myproject.project.model.response.FoodServiceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FoodServiceMapper implements Mapper<FoodServiceMapper>{

    public FoodService to(FoodServiceRequest request) {
        FoodService foodService = new FoodService();
        BeanUtils.copyProperties(request,foodService);
        return foodService;
    }

    public FoodServiceResponse to(FoodService foodService) {
        FoodServiceResponse foodServiceResponse = new FoodServiceResponse();
        BeanUtils.copyProperties(foodService, foodServiceResponse);
        return foodServiceResponse;
    }
}
