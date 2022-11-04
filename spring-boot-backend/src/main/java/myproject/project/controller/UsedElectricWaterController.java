package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.UsedElectricWaterRequest;
import myproject.project.model.response.BaseResponse;
import myproject.project.model.response.UsedElectricWaterResponse;
import myproject.project.service.UsedEletricWaterService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("public-api/v1.0.0/electric-water")
public class UsedElectricWaterController {
    private final UsedEletricWaterService usedEletricWaterService;

    @PostMapping("/create")
    BaseResponse<UsedElectricWaterResponse> createUsedElectricWater(@RequestBody @Valid UsedElectricWaterRequest request){
        return BaseResponse.ofSuccess(usedEletricWaterService.save(request));
    }

    @GetMapping("/list")
    BaseResponse<List<UsedElectricWaterResponse>> getAllUsedElectricWater(){
        return BaseResponse.ofSuccess(usedEletricWaterService.getAllUsedElectricWater());
    }
}
