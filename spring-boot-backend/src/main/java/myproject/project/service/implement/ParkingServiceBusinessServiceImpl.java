package myproject.project.service.implement;

import lombok.extern.log4j.Log4j2;
import myproject.project.entity.ParkingService;
import myproject.project.entity.UsedService;
import myproject.project.model.request.ParkingServiceRequest;
import myproject.project.exception.BusinessCode;
import myproject.project.exception.BusinessException;
import myproject.project.mapper.ParkingServiceMapper;
import myproject.project.model.response.ParkingServiceResponse;
import myproject.project.repository.ServiceRepository;
import myproject.project.repository.UsedServiceRepository;
import myproject.project.service.ParkingServiceBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ParkingServiceBusinessServiceImpl implements ParkingServiceBusinessService {

    private final ServiceRepository serviceRepository;

    private final ParkingServiceMapper parkingServiceMapper;

    private final UsedServiceRepository usedServiceRepository;

    public ParkingServiceBusinessServiceImpl(ServiceRepository serviceRepository, ParkingServiceMapper parkingServiceMapper, UsedServiceRepository usedServiceRepository) {
        this.serviceRepository = serviceRepository;
        this.parkingServiceMapper = parkingServiceMapper;
        this.usedServiceRepository = usedServiceRepository;
    }

    @Override
    public ParkingServiceResponse getActiveParkingService() {
        ParkingService parkingService = serviceRepository.findParkingServiceByActiveIs(true).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CURRENT_SERVICE)
        );
        return parkingServiceMapper.to(parkingService);
    }

    @Override
    @Transactional
    public ParkingServiceResponse createNewParkingService(ParkingServiceRequest request) {
        request.setType("parking");
        if(serviceRepository.findParkingServiceByActiveIs(true).isPresent()) {
            ParkingService oldService = serviceRepository.findParkingServiceByActiveIs(true).get();
            List<UsedService> usedServiceList = usedServiceRepository.findByService(oldService);
            usedServiceRepository.deleteUsedServiceByService(oldService);
            serviceRepository.deactivateAllParkingService();
            ParkingService newService = serviceRepository.save(parkingServiceMapper.to(request));
            usedServiceList = usedServiceList.stream().map(usedService -> {
                UsedService newUsedService = new UsedService();
                newUsedService.setService(newService);
                newUsedService.setCompany(usedService.getCompany());
                newUsedService.setStartDate(new Timestamp((new Date()).getTime()));
                return newUsedService;
            }).collect(Collectors.toList());
            usedServiceRepository.saveAll(usedServiceList);
            return parkingServiceMapper.to(newService);
        } else {
            ParkingService newService = serviceRepository.save(parkingServiceMapper.to(request));
            return parkingServiceMapper.to(newService);
        }
    }
}
