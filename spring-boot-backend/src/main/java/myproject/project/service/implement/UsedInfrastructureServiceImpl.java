package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import myproject.project.entity.UsedInfrastructure;
import myproject.project.mapper.UsedInfrastructureMapper;
import myproject.project.model.request.UsedInfrastructureRequest;
import myproject.project.model.response.UsedInfrastructureResponse;
import myproject.project.repository.UsedInfrastructureRepository;
import myproject.project.service.UsedInfrastructureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@AllArgsConstructor
public class UsedInfrastructureServiceImpl implements UsedInfrastructureService {
    private final UsedInfrastructureRepository repository;
    private final UsedInfrastructureMapper mapper;

    @Override
    public UsedInfrastructureResponse save(UsedInfrastructureRequest request) {
        UsedInfrastructure usedInfrastructure = repository.saveAndFlush(mapper.to(request));
        return mapper.to(usedInfrastructure);
    }

    @Override
    @Transactional
    public String deleteByCompanyIdAndInfrastructureId(Long companyId, Long infrastructureIdId) {
        UsedInfrastructure usedInfrastructure = repository.findByIsDeletedAndCompanyIdAndInfrastructureId(false, companyId, infrastructureIdId);
        System.out.println(usedInfrastructure);
        usedInfrastructure.setIsDeleted(true);
        repository.saveAndFlush(usedInfrastructure);
        return "Deleted";
    }

    @Override
    @Transactional
    public UsedInfrastructureResponse updateById(Long companyId, Long infrastructureIdId, UsedInfrastructureRequest request) {
        UsedInfrastructure usedInfrastructure = repository.findByIsDeletedAndCompanyIdAndInfrastructureId(false, companyId, infrastructureIdId);
        System.out.println(usedInfrastructure);
        return mapper.to(repository.saveAndFlush(mapper.to(usedInfrastructure,request)));
    }


}
