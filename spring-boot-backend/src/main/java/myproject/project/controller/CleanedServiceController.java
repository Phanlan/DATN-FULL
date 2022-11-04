package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.CleanedRequest;
import myproject.project.service.CleanedServiceBusinessService;
import myproject.project.model.response.BaseResponse;
import myproject.project.model.response.CleanedResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("public-api/v1.0.0/cleaned-service")
@AllArgsConstructor
public class CleanedServiceController {

    private final CleanedServiceBusinessService serviceBusinessService;

    @GetMapping()
    public BaseResponse<CleanedResponse> getCurrentCleanedService(){
        return BaseResponse.ofSuccess(serviceBusinessService.getActiveCleanedService());
    }

    @PostMapping()
    public BaseResponse<CleanedResponse> createNewCleanedService(@RequestBody @Valid CleanedRequest cleanedRequest){
        return BaseResponse.ofSuccess(serviceBusinessService.createNewCleanedService(cleanedRequest));
    }
}
