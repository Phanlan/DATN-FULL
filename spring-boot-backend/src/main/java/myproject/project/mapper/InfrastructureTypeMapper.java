package myproject.project.mapper;

import lombok.AllArgsConstructor;
import myproject.project.entity.InfrastructureType;
import myproject.project.model.request.InfrastructureTypeRequest;
import myproject.project.model.response.InfrastructureTypeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InfrastructureTypeMapper implements Mapper<InfrastructureType> {
    public InfrastructureType to(InfrastructureTypeRequest request) {
        InfrastructureType infrastructureType = new InfrastructureType();
        BeanUtils.copyProperties(request, infrastructureType);
        return infrastructureType;
    }

    public InfrastructureTypeResponse to(InfrastructureType infrastructureType) {
        InfrastructureTypeResponse response = new InfrastructureTypeResponse();
        BeanUtils.copyProperties(infrastructureType, response);
        return response;
    }
}
