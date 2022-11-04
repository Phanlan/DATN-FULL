package myproject.project.mapper;

import myproject.project.model.request.ServiceRequest;
import myproject.project.entity.Service;
import myproject.project.model.response.ServiceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper implements Mapper<Service> {
    public Service to(ServiceRequest request) {
        Service service = new Service();
        BeanUtils.copyProperties(request, service);
        return service;
    }

    public ServiceResponse to(Service service) {
        ServiceResponse response = new ServiceResponse();
        BeanUtils.copyProperties(service, response);
        return response;
    }
}
