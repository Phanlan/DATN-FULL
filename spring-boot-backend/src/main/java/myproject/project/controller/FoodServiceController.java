package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.FoodServiceRequest;
import myproject.project.service.FoodServiceBusinessService;
import myproject.project.model.response.BaseResponse;
import myproject.project.model.response.FoodServiceResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("public-api/v1.0.0/food-service")
@AllArgsConstructor
public class FoodServiceController {

    private final FoodServiceBusinessService foodService;

    @GetMapping()
    public BaseResponse<FoodServiceResponse> getCurrentFoodService() {
        return BaseResponse.ofSuccess(foodService.getActiveFoodService());
    }

    @PostMapping()
    public BaseResponse<FoodServiceResponse> createNewFoodService(@RequestBody @Valid FoodServiceRequest request) {
        return BaseResponse.ofSuccess(foodService.createNewFoodService(request));
    }
}
