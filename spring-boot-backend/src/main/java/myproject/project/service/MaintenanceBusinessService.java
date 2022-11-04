package myproject.project.service;

import myproject.project.model.request.MaintenanceRequest;
import myproject.project.model.response.MaintenanceResponse;

public interface MaintenanceBusinessService {
    MaintenanceResponse getActiveMaintenanceService();
    MaintenanceResponse createNewMaintenanceService(MaintenanceRequest maintenanceRequest);
}
