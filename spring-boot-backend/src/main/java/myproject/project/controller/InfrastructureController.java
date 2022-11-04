package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.InfrastructureRequest;
import myproject.project.model.request.UsedInfrastructureRequest;
import myproject.project.model.response.*;
import myproject.project.service.InfrastructureService;
import myproject.project.service.InfrastructureTypeService;
import myproject.project.service.UsedInfrastructureService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("public-api/v1.0.0/infrastructure")
public class InfrastructureController {
    private final InfrastructureService infrastructureService;
    private final InfrastructureTypeService infrastructureTypeService;
    private final UsedInfrastructureService usedInfrastructureService;

    @GetMapping("{id}")
    BaseResponse<InfrastructureDetailResponse> getInfrastructureById(@PathVariable Long id){
        return BaseResponse.ofSuccess(infrastructureService.findById(id));
    }

    @GetMapping("/list")
    BaseResponse<List<InfrastructureResponse>> getAllInfrastructure(){
        return BaseResponse.ofSuccess(infrastructureService.getAllInfrastructure());
    }

    @PutMapping("/update/{id}")
    BaseResponse<InfrastructureResponse> updateInfrastructure(@PathVariable Long id, @RequestBody @Valid InfrastructureRequest request){
        return BaseResponse.ofSuccess(infrastructureService.updateById(id,request));
    }

    @DeleteMapping("/delete/{id}")
    BaseResponse<String> deleteInfrastructure(@PathVariable Long id){
        return BaseResponse.ofSuccess(infrastructureService.deleteById(id));
    }

    @PostMapping("/create")
    BaseResponse<InfrastructureResponse> createInfrastructure(@RequestBody @Valid InfrastructureRequest request){
        return BaseResponse.ofSuccess(infrastructureService.save(request));
    }

    @GetMapping("/list-infastructureType")
    BaseResponse<List<InfrastructureTypeResponse>> getAllInfrastructureType(){
        return BaseResponse.ofSuccess(infrastructureTypeService.getAllInfrastructureType());
    }

    @PostMapping("/{infrastructure_id}/add/company/{company_id}/{quantity}")
    BaseResponse<UsedInfrastructureResponse> createCompanyUsed(@PathVariable Long company_id, @PathVariable Long infrastructure_id, @PathVariable Integer quantity){
        UsedInfrastructureRequest request = new UsedInfrastructureRequest(company_id,infrastructure_id, quantity);
        return BaseResponse.ofSuccess(usedInfrastructureService.save(request));
    }

    @DeleteMapping("/{infrastructure_id}/company/delete/{company_id}")
    BaseResponse<String> deleteCompanyUsed(@PathVariable Long company_id, @PathVariable Long infrastructure_id){
        return BaseResponse.ofSuccess(usedInfrastructureService.deleteByCompanyIdAndInfrastructureId(company_id,infrastructure_id));
    }

    @PutMapping("/{infrastructure_id}/company/update/{company_id}")
    public BaseResponse<UsedInfrastructureResponse> updateCompanyUsedById(@PathVariable Long company_id,@PathVariable  Long infrastructure_id,@RequestBody UsedInfrastructureRequest request){
        request.setCompanyId(company_id);
        return BaseResponse.ofSuccess(usedInfrastructureService.updateById(company_id,infrastructure_id,request));
    }


}
