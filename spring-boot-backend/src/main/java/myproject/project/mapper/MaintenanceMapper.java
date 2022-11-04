package myproject.project.mapper;

import myproject.project.model.request.MaintenanceRequest;
import myproject.project.entity.MaintenanceService;
import myproject.project.model.response.MaintenanceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceMapper implements Mapper<MaintenanceMapper>{

    public MaintenanceService to(MaintenanceRequest request) {
        MaintenanceService maintenanceService = new MaintenanceService();
        BeanUtils.copyProperties(request, maintenanceService);
        return maintenanceService;
    }

    public MaintenanceResponse to(MaintenanceService maintenanceService) {
        MaintenanceResponse response = new MaintenanceResponse();
        BeanUtils.copyProperties(maintenanceService, response);
        return response;
    }
}
