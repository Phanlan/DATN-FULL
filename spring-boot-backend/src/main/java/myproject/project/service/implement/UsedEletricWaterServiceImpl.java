package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import myproject.project.entity.UsedElectricWater;
import myproject.project.mapper.UsedElectricWaterMapper;
import myproject.project.model.request.UsedElectricWaterRequest;
import myproject.project.model.response.UsedElectricWaterResponse;
import myproject.project.repository.UsedElectricWaterRepository;
import myproject.project.service.UsedEletricWaterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class UsedEletricWaterServiceImpl implements UsedEletricWaterService {

    private final UsedElectricWaterMapper mapper;
    private final UsedElectricWaterRepository repository;

    @Override
    @Transactional
    public UsedElectricWaterResponse save(UsedElectricWaterRequest request) {
        UsedElectricWater usedElectricWater = mapper.to(request);
        return mapper.to(repository.saveAndFlush(usedElectricWater));
    }

    @Override
    public List<UsedElectricWaterResponse> getAllUsedElectricWater() {
        List<UsedElectricWater> vehicleList = repository.findAllByIsDeleted(false);
        return vehicleList.stream().map(mapper::to).collect(Collectors.toList());
    }
}
