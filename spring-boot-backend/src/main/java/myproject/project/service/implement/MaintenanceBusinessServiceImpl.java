package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import myproject.project.entity.UsedService;
import myproject.project.model.request.MaintenanceRequest;
import myproject.project.entity.MaintenanceService;
import myproject.project.exception.BusinessCode;
import myproject.project.exception.BusinessException;
import myproject.project.mapper.MaintenanceMapper;
import myproject.project.model.response.MaintenanceResponse;
import myproject.project.repository.ServiceRepository;
import myproject.project.repository.UsedServiceRepository;
import myproject.project.service.MaintenanceBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaintenanceBusinessServiceImpl implements MaintenanceBusinessService {

    private final ServiceRepository serviceRepository;

    private final MaintenanceMapper maintenanceMapper;

    private final UsedServiceRepository usedServiceRepository;

    @Override
    public MaintenanceResponse getActiveMaintenanceService() {
        MaintenanceService maintenanceService = serviceRepository.findMaintenanceServiceByActiveIs(true).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CURRENT_SERVICE)
        );
        return maintenanceMapper.to(maintenanceService);
    }

    @Override
    @Transactional
    public MaintenanceResponse createNewMaintenanceService(MaintenanceRequest request) {
        request.setType("maintenance");
        if(serviceRepository.findMaintenanceServiceByActiveIs(true).isPresent()) {
            MaintenanceService oldService = serviceRepository.findMaintenanceServiceByActiveIs(true).get();
            List<UsedService> usedServiceList = usedServiceRepository.findByService(oldService);
            usedServiceRepository.deleteUsedServiceByService(oldService);
            serviceRepository.deactivateAllMaintenanceService();
            MaintenanceService newService = serviceRepository.save(maintenanceMapper.to(request));
            usedServiceList = usedServiceList.stream().map(usedService -> {
                UsedService newUsedService = new UsedService();
                newUsedService.setService(newService);
                newUsedService.setCompany(usedService.getCompany());
                newUsedService.setStartDate(new Timestamp((new Date()).getTime()));
                return newUsedService;
            }).collect(Collectors.toList());
            usedServiceRepository.saveAll(usedServiceList);
            return maintenanceMapper.to(newService);
        } else {
            MaintenanceService newService = serviceRepository.save(maintenanceMapper.to(request));
            return maintenanceMapper.to(newService);
        }
    }
}
