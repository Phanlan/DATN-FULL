package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import myproject.project.entity.Infrastructure;
import myproject.project.entity.UsedInfrastructure;
import myproject.project.exception.BusinessCode;
import myproject.project.exception.BusinessException;
import myproject.project.mapper.InfrastructureMapper;
import myproject.project.model.request.InfrastructureRequest;
import myproject.project.model.response.InfrastructureDetailResponse;
import myproject.project.model.response.InfrastructureResponse;
import myproject.project.repository.InfrastructureRepository;
import myproject.project.repository.UsedInfrastructureRepository;
import myproject.project.service.InfrastructureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class InfrastructureServiceImpl implements InfrastructureService {

    private final InfrastructureRepository infrastructureRepository;
    private final InfrastructureMapper infrastructureMapper;
    private final UsedInfrastructureRepository usedInfrastructureRepository;

    @Override
    public InfrastructureResponse save(InfrastructureRequest request) {
        Infrastructure infrastructure = infrastructureRepository.saveAndFlush(infrastructureMapper.to(request));
        return infrastructureMapper.to(infrastructure);
    }

    @Override
    public InfrastructureDetailResponse findById(Long id) {
        Infrastructure infrastructure = infrastructureRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );
        List<UsedInfrastructure> usedInfrastructureList = usedInfrastructureRepository.findByIsDeletedAndInfrastructureId(false,id);
        infrastructure.setUsedInfrastructureList(usedInfrastructureList);
        return infrastructureMapper.toDetail(infrastructure);
    }
    @Override
    public List<InfrastructureResponse> getAllInfrastructure() {
        List<Infrastructure> infrastructureList = infrastructureRepository.findAllByIsDeleted(false);
        return infrastructureList.stream().map(infrastructureMapper::to).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public InfrastructureResponse updateById(Long id, InfrastructureRequest request) {
        Infrastructure infrastructure = infrastructureRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );
        return infrastructureMapper.to(infrastructureRepository.saveAndFlush(infrastructureMapper.to(infrastructure,request)));
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        Infrastructure infrastructure = infrastructureRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_BUILDING_EMPLOYEE)
        );
        infrastructure.setIsDeleted(true);
        infrastructureRepository.save(infrastructure);
        return "Deleted";
    }

}
