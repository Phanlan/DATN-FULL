package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.ProtectedServiceRequest;
import myproject.project.service.ProtectedServiceBusinessService;
import myproject.project.model.response.BaseResponse;
import myproject.project.model.response.ProtectedServiceResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("public-api/v1.0.0/protect-service")
@AllArgsConstructor
public class ProtectedServiceController {
    private final ProtectedServiceBusinessService protectedService;

    @GetMapping()
    public BaseResponse<ProtectedServiceResponse> getProtectedServiceCurrent() {
        return BaseResponse.ofSuccess(protectedService.getActiveProtectedService());
    }

    @PostMapping()
    public BaseResponse<ProtectedServiceResponse> createNewProtectedService(@RequestBody @Valid ProtectedServiceRequest request) {
        return BaseResponse.ofSuccess((protectedService.createNewProtectedService(request)));
    }
}
