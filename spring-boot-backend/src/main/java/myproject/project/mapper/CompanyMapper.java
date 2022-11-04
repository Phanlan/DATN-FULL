package myproject.project.mapper;

import lombok.AllArgsConstructor;
import myproject.project.entity.Company;
import myproject.project.entity.UsedInfrastructure;
import myproject.project.entity.UsedService;
import myproject.project.model.request.CompanyRequest;
import myproject.project.model.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CompanyMapper implements Mapper<Company> {
    private final CompanyEmployeeMapper companyEmployeeMapper;
    private final UsedElectricWaterMapper usedElectricWaterMapper;
    private final VehicleMapper vehicleMapper;

    public Company to(CompanyRequest request) {
        Company company = new Company();
        BeanUtils.copyProperties(request, company);
        return company;
    }

    public Company to(Company company, CompanyRequest request) {
        BeanUtils.copyProperties(request, company);
        return company;
    }

    public CompanyResponse to(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        if (company.getCompanyEmployeeList() != null) {
            Long numberOfEmployee = Long.valueOf(company.getCompanyEmployeeList().size());
            companyResponse.setNumberOfEmployee(numberOfEmployee);
        }
        return companyResponse;
    }

    public CompanyDetailResponse toDetail(Company company) {
        CompanyDetailResponse companyDetailResponse = new CompanyDetailResponse();
        BeanUtils.copyProperties(company, companyDetailResponse);
        if (company.getCompanyEmployeeList() != null) {
            List<CompanyEmployeeResponse> companyEmployeeResponseList = companyEmployeeMapper.toList(company.getCompanyEmployeeList(), companyEmployeeMapper::to);
            companyDetailResponse.setCompanyEmployeeList(companyEmployeeResponseList);
        }
        if (company.getUsedElectricWaterList() != null) {
            List<UsedElectricWaterResponse> usedElectricWaterResponseList = usedElectricWaterMapper.toList(company.getUsedElectricWaterList(), usedElectricWaterMapper::to);
            companyDetailResponse.setUsedElectricWaterList(usedElectricWaterResponseList);
        }

        if (company.getVehicleList() != null) {
            List<VehicleResponse> vehicleResponseList = vehicleMapper.toList(company.getVehicleList(), vehicleMapper::to);
            companyDetailResponse.setVehicleResponseList(vehicleResponseList);
        }
        if (company.getUsedServiceList() != null) {
            List<ServiceResponse> serviceList = new ArrayList<>();
            System.out.println(company.getUsedServiceList());
            for (UsedService usedService : company.getUsedServiceList()) {
                ServiceResponse serviceResponse = new ServiceResponse();
                serviceResponse.setId(usedService.getService().getId());
                serviceResponse.setName(usedService.getService().getName());
                serviceResponse.setPrice(usedService.getService().getPrice());
                serviceList.add(serviceResponse);
            }
            System.out.println(serviceList);
            companyDetailResponse.setServiceList(serviceList);
        }
        if (company.getUsedInfrastructureList() != null) {
            List<InfrastructureResponse> infrastructureResponseList = new ArrayList<>();
            for (UsedInfrastructure usedInfrastructure : company.getUsedInfrastructureList()) {
                InfrastructureResponse infrastructureResponse = new InfrastructureResponse();
                infrastructureResponse.setId(usedInfrastructure.getInfrastructure().getId());
                infrastructureResponse.setQuantity(usedInfrastructure.getInfrastructure().getQuantity());
                infrastructureResponse.setQuantityCompanyUse(usedInfrastructure.getQuantity());
                infrastructureResponse.setName(usedInfrastructure.getInfrastructure().getName());
                infrastructureResponse.setType(usedInfrastructure.getInfrastructure().getType());
                infrastructureResponse.setUnitAmount(usedInfrastructure.getInfrastructure().getUnitAmount());
                infrastructureResponseList.add(infrastructureResponse);
            }
            companyDetailResponse.setInfrastructureList(infrastructureResponseList);
            System.out.println(infrastructureResponseList);

        }
        return companyDetailResponse;
    }
}
