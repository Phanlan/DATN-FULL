package myproject.project.service.implement;

import lombok.extern.log4j.Log4j2;
import myproject.project.entity.UsedService;
import myproject.project.model.request.FoodServiceRequest;
import myproject.project.entity.FoodService;
import myproject.project.exception.BusinessCode;
import myproject.project.exception.BusinessException;
import myproject.project.mapper.FoodServiceMapper;
import myproject.project.model.response.FoodServiceResponse;
import myproject.project.repository.ServiceRepository;
import myproject.project.repository.UsedServiceRepository;
import myproject.project.service.FoodServiceBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FoodServiceBusinessServiceImpl implements FoodServiceBusinessService {

    private final ServiceRepository serviceRepository;

    private final FoodServiceMapper foodServiceMapper;

    private final UsedServiceRepository usedServiceRepository;

    public FoodServiceBusinessServiceImpl(ServiceRepository serviceRepository, FoodServiceMapper foodServiceMapper, UsedServiceRepository usedServiceRepository) {
        this.serviceRepository = serviceRepository;
        this.foodServiceMapper = foodServiceMapper;
        this.usedServiceRepository = usedServiceRepository;
    }

    @Override
    public FoodServiceResponse getActiveFoodService() {
        FoodService foodService = serviceRepository.findFoodServiceByActiveIs(true).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CURRENT_SERVICE)
        );
        return foodServiceMapper.to(foodService);
    }

    @Override
    @Transactional
    public FoodServiceResponse createNewFoodService(FoodServiceRequest request) {
        request.setType("food");
        if(serviceRepository.findFoodServiceByActiveIs(true).isPresent()) {
            FoodService oldService = serviceRepository.findFoodServiceByActiveIs(true).get();
            List<UsedService> usedServiceList = usedServiceRepository.findByService(oldService);
            usedServiceRepository.deleteUsedServiceByService(oldService);
            serviceRepository.deactivateAllFoodService();
            FoodService newService = serviceRepository.save(foodServiceMapper.to(request));
            usedServiceList = usedServiceList.stream().map(usedService -> {
                UsedService newUsedService = new UsedService();
                newUsedService.setService(newService);
                newUsedService.setCompany(usedService.getCompany());
                newUsedService.setStartDate(new Timestamp((new Date()).getTime()));
                return newUsedService;
            }).collect(Collectors.toList());
            usedServiceRepository.saveAll(usedServiceList);
            return foodServiceMapper.to(newService);
        } else {
            FoodService newService = serviceRepository.save(foodServiceMapper.to(request));
            return foodServiceMapper.to(newService);
        }
    }
}
