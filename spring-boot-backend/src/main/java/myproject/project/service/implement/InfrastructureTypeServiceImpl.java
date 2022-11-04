package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import myproject.project.entity.InfrastructureType;
import myproject.project.mapper.InfrastructureTypeMapper;
import myproject.project.model.response.InfrastructureTypeResponse;
import myproject.project.repository.InfrastructureTypeRepository;
import myproject.project.service.InfrastructureTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InfrastructureTypeServiceImpl implements InfrastructureTypeService {
    private InfrastructureTypeRepository infrastructureTypeRepository;
    private InfrastructureTypeMapper mapper;

    @Override
    public List<InfrastructureTypeResponse> getAllInfrastructureType() {

        List<InfrastructureType> infrastructuretypeList = infrastructureTypeRepository.findAllByIsDeleted(false);
        return infrastructuretypeList.stream().map(mapper::to).collect(Collectors.toList());
    }
}
