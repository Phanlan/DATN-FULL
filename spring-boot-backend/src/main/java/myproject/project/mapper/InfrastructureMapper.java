package myproject.project.mapper;

import lombok.AllArgsConstructor;
import myproject.project.entity.Infrastructure;
import myproject.project.entity.UsedInfrastructure;
import myproject.project.model.request.InfrastructureRequest;
import myproject.project.model.response.CompanyResponse;
import myproject.project.model.response.InfrastructureDetailResponse;
import myproject.project.model.response.InfrastructureResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class InfrastructureMapper implements Mapper<Infrastructure> {

    public  Infrastructure to(InfrastructureRequest request){
        Infrastructure infrastructure = new Infrastructure();
        BeanUtils.copyProperties(request,infrastructure);
        return infrastructure;
    }

    public Infrastructure to(Infrastructure infrastructure, InfrastructureRequest request){
        BeanUtils.copyProperties(request, infrastructure);
        return infrastructure;
    }

    public InfrastructureResponse to(Infrastructure infrastructure){
        InfrastructureResponse response = new InfrastructureResponse();
        BeanUtils.copyProperties(infrastructure, response);
        return response;
    }

    public InfrastructureDetailResponse toDetail(Infrastructure infrastructure){
        InfrastructureDetailResponse infrastructureDetailResponse = new InfrastructureDetailResponse();
        BeanUtils.copyProperties(infrastructure,infrastructureDetailResponse);
        if(infrastructure.getUsedInfrastructureList() != null){
            List<CompanyResponse> companyResponseList = new ArrayList<>();
            for (UsedInfrastructure usedInfrastructure: infrastructure.getUsedInfrastructureList()){
                CompanyResponse companyResponse = new CompanyResponse();
                companyResponse.setId(usedInfrastructure.getCompany().getId());
                companyResponse.setName(usedInfrastructure.getCompany().getName());
                companyResponse.setTaxCode(usedInfrastructure.getCompany().getTaxCode());
                companyResponse.setFieldOfActivity(usedInfrastructure.getCompany().getFieldOfActivity());
                companyResponse.setFloor(usedInfrastructure.getCompany().getFloor());
                companyResponse.setHotline(usedInfrastructure.getCompany().getHotline());
                companyResponse.setArea(usedInfrastructure.getCompany().getArea());
                companyResponse.setQuantityInfrastructure(usedInfrastructure.getQuantity());
                companyResponseList.add(companyResponse);
            }
            infrastructureDetailResponse.setCompanyList(companyResponseList);

        }
        return infrastructureDetailResponse;
    }
}
