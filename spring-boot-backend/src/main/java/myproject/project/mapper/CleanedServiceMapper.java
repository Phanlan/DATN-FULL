package myproject.project.mapper;

import myproject.project.entity.CleanedService;
import myproject.project.model.request.CleanedRequest;
import myproject.project.model.response.CleanedResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CleanedServiceMapper implements Mapper<CleanedServiceMapper>{

    public CleanedService to(CleanedRequest request) {
        CleanedService cleanedService = new CleanedService();
        BeanUtils.copyProperties(request, cleanedService);
        return cleanedService;
    }

    public CleanedResponse to(CleanedService cleanedService) {
        CleanedResponse response = new CleanedResponse();
        BeanUtils.copyProperties(cleanedService, response);
        return response;
    }
}
