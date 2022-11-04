package myproject.project.mapper;

import lombok.AllArgsConstructor;
import myproject.project.entity.Company;
import myproject.project.entity.Service;
import myproject.project.entity.UsedElectricWater;
import myproject.project.entity.UsedService;
import myproject.project.model.request.UsedServiceRequest;
import myproject.project.model.response.UsedServiceResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
@AllArgsConstructor
public class UsedServiceMapper implements Mapper<UsedService> {

    public UsedService to(UsedServiceRequest request){
        UsedService usedService = new UsedService();
        Service service = new Service();
        service.setId(request.getServiceId());
        Company company = new Company();
        company.setId(request.getCompanyId());
        UsedElectricWater usedElectricWater = new UsedElectricWater();
        usedService.setService(service);
        usedService.setCompany(company);
        usedService.setStartDate(new Timestamp(System.currentTimeMillis()));
        return  usedService;
    }

    public UsedServiceResponse to(UsedService usedService){
        UsedServiceResponse usedServiceResponse = new UsedServiceResponse();
        usedServiceResponse.setServiceId(usedService.getService().getId());
        usedServiceResponse.setCompanyId(usedService.getCompany().getId());
        usedServiceResponse.setId(usedService.getId());
        usedServiceResponse.setStartDate(usedService.getStartDate().toString());
        return usedServiceResponse;
    }
}
