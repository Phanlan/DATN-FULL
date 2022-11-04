package myproject.project.mapper;

import lombok.AllArgsConstructor;
import myproject.project.entity.Company;
import myproject.project.entity.Infrastructure;
import myproject.project.entity.UsedInfrastructure;
import myproject.project.model.request.UsedInfrastructureRequest;
import myproject.project.model.response.UsedInfrastructureResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsedInfrastructureMapper implements Mapper<UsedInfrastructure>{
    public UsedInfrastructure to(UsedInfrastructureRequest request){
        UsedInfrastructure usedInfrastructure = new UsedInfrastructure();
        Infrastructure infrastructure = new Infrastructure();
        infrastructure.setId(request.getInfrastructureId());
        Company company = new Company();
        company.setId(request.getCompanyId());
        usedInfrastructure.setInfrastructure(infrastructure);
        usedInfrastructure.setCompany(company);
        usedInfrastructure.setQuantity(request.getQuantity());
        System.out.println(request);
        return  usedInfrastructure;
    }
    public UsedInfrastructureResponse to(UsedInfrastructure usedInfrastructure){
        UsedInfrastructureResponse usedInfrastructureResponse = new UsedInfrastructureResponse();
        usedInfrastructureResponse.setInfrastructureId(usedInfrastructure.getInfrastructure().getId());
        usedInfrastructureResponse.setCompanyId(usedInfrastructure.getCompany().getId());
        usedInfrastructureResponse.setId(usedInfrastructure.getId());
        usedInfrastructureResponse.setQuantity(usedInfrastructure.getQuantity());

        System.out.println(usedInfrastructureResponse);
        return usedInfrastructureResponse;
    }

    public UsedInfrastructure to(UsedInfrastructure usedInfrastructure, UsedInfrastructureRequest request){
        BeanUtils.copyProperties(request,usedInfrastructure);
        return usedInfrastructure;
    }
}


