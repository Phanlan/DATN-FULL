package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.*;
import myproject.project.model.response.*;
import myproject.project.service.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("public-api/v1.0.0/company")
public class CompanyController {

    private final CompanyService companyService;
    private final UsedServiceBusinessService usedService;
    private final ServiceBusinessService service;
    private final UsedInfrastructureService usedInfrastructureService;
    private final CompanyEmployeeService companyEmployeeService;
    private final MonthStatCompany monthStatCompany;

    private final UsedEletricWaterService usedEletricWaterService;

    @GetMapping("{id}")
    BaseResponse<CompanyDetailResponse> getCompanyById(@PathVariable Long id){
        return BaseResponse.ofSuccess(companyService.findById(id));
    }

    @GetMapping("/list")
    BaseResponse<List<CompanyResponse>> getAllCompany(){
        return BaseResponse.ofSuccess(companyService.getAllCompany());
    }

    @PutMapping("/update/{id}")
    BaseResponse<CompanyResponse> updateCompany(@PathVariable Long id, @RequestBody @Valid CompanyRequest request){
        return BaseResponse.ofSuccess(companyService.updateById(id,request));
    }

    @PostMapping("/create")
    BaseResponse<CompanyResponse> createCompany(@RequestBody @Valid CompanyRequest request){
        return BaseResponse.ofSuccess(companyService.save(request));
    }

    @DeleteMapping("/delete/{id}")
    BaseResponse<String> deleteCompany(@PathVariable Long id){
        return BaseResponse.ofSuccess(companyService.deleteById(id));
    }

    @GetMapping("/search")
    BaseResponse<List<CompanyResponse>> findCompanyByNameLike(@RequestParam String name){
        return BaseResponse.ofSuccess(companyService.findCompanyByNameLike(name));
    }

    @PostMapping("/{company_id}/add-service/{service_id}")
    BaseResponse<UsedServiceResponse> createUsedService(@PathVariable Long company_id, @PathVariable Long service_id){
        UsedServiceRequest request = new UsedServiceRequest(company_id,service_id);
        return BaseResponse.ofSuccess(usedService.save(request));
    }

    @DeleteMapping("/{company_id}/used-service/delete/{service_id}")
    BaseResponse<String> deleteUsedService(@PathVariable Long company_id, @PathVariable Long service_id){
        return BaseResponse.ofSuccess(usedService.deleteByCompanyIdAndServiceId(company_id,service_id));
    }

    @GetMapping("/{company_id}/used-service/month/{month}")
    BaseResponse<List<UsedServiceResponse>> listUsedServiceMonth(@PathVariable Long company_id, @PathVariable Long month){
        return BaseResponse.ofSuccess(usedService.findUsedServiceMonthByCompany(company_id,month));
    }

    @GetMapping("/{company_id}/used-service")
    BaseResponse<List<ServiceResponse>> listServiceNotUsed(@PathVariable Long company_id){
        return BaseResponse.ofSuccess(service.findServiceNotUsedByCompanyId(company_id));
    }

    @PostMapping("/{company_id}/employee/create")
    public BaseResponse<CompanyEmployeeResponse> createCompanyEmployee(@PathVariable Long company_id, @RequestBody @Valid CompanyEmployeeRequest request){
        request.setCompany_id(company_id);
        return BaseResponse.ofSuccess(companyEmployeeService.save(request));
    }

    @PutMapping("/{company_id}/employee/update/{employee_id}")
    public BaseResponse<CompanyEmployeeResponse> updateCompanyEmployeeById(@PathVariable Long company_id,@PathVariable  Long employee_id,@RequestBody CompanyEmployeeRequest request){
        request.setCompany_id(company_id);
        return BaseResponse.ofSuccess(companyEmployeeService.updateById(employee_id,request));
    }

    @GetMapping("/{company_id}/employee/{employee_id}")
    public  BaseResponse<CompanyEmployeeResponse> getCompanyEmployeeById(@PathVariable Long employee_id, @PathVariable String company_id){
        return BaseResponse.ofSuccess(companyEmployeeService.findById(employee_id));
    }

    @GetMapping("/{company_id}/employee")
    public BaseResponse<List<CompanyEmployeeResponse>> getCompanyEmployeeByCompanyId(@PathVariable Long company_id){
        return BaseResponse.ofSuccess(companyEmployeeService.findByCompanyId(company_id));
    }

    @GetMapping("/{company_id}/employee/list")
    public BaseResponse<List<CompanyEmployeeResponse>> getAllCompanyEmployee(@PathVariable String company_id){
        return BaseResponse.ofSuccess(companyEmployeeService.getAllCompanyEmployee());
    }

    @DeleteMapping("/{company_id}/employee/delete/{id}")
    public BaseResponse<String> deleteCompanyEmployeeById(@PathVariable Long id, @PathVariable String company_id){
        return BaseResponse.ofSuccess((companyEmployeeService.deleteById(id)));
    }

    @GetMapping("/{company_id}/employee/search")
    BaseResponse<List<CompanyEmployeeResponse>> findCompanyEmployeeByNameLike(@PathVariable Long company_id, @RequestParam String name){
        return BaseResponse.ofSuccess(companyEmployeeService.findCompanyEmployeeByCompanyAndNameLike(company_id,name));
    }

    @PostMapping("/{company_id}/used_electric_water/create")
    public BaseResponse<UsedElectricWaterResponse> createUsedElectricWater(@PathVariable Long company_id, @RequestBody @Valid UsedElectricWaterRequest request){
        request.setCompany_id(company_id);
        return BaseResponse.ofSuccess(usedEletricWaterService.save(request));
    }

    @GetMapping("/view-statistic/{month}/{year}")
    BaseResponse<List<MonthStatCompanyResponse>> viewStatistic(@PathVariable int month, @PathVariable int year) {
        return BaseResponse.ofSuccess(monthStatCompany.viewStatistic(month, year));
    }

    @PostMapping("/{company_id}/add/infrastructure/{infrastructure_id}/{quantity}")
    BaseResponse<UsedInfrastructureResponse> createUsedInfrastructure(@PathVariable Long company_id, @PathVariable Long infrastructure_id, @PathVariable Integer quantity){
        UsedInfrastructureRequest request = new UsedInfrastructureRequest(company_id,infrastructure_id, quantity);
        return BaseResponse.ofSuccess(usedInfrastructureService.save(request));
    }

    @DeleteMapping("/{company_id}/infrastructure/delete/{infrastructure_id}")
    BaseResponse<String> deleteUsedInfrastructure(@PathVariable Long company_id, @PathVariable Long infrastructure_id){
        return BaseResponse.ofSuccess(usedInfrastructureService.deleteByCompanyIdAndInfrastructureId(company_id,infrastructure_id));
    }

    @PutMapping("/{company_id}/infrastructure/update/{infrastructure_id}")
    public BaseResponse<UsedInfrastructureResponse> updateUsedInfrastructureById(@PathVariable Long company_id,@PathVariable  Long infrastructure_id,@RequestBody UsedInfrastructureRequest request){
        request.setCompanyId(company_id);
        request.setInfrastructureId(infrastructure_id);
        return BaseResponse.ofSuccess(usedInfrastructureService.updateById(company_id,infrastructure_id,request));
    }

}
