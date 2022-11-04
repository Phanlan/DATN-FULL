package myproject.project.mapper;

import lombok.AllArgsConstructor;
import myproject.project.entity.VehicleType;
import myproject.project.model.request.VehicleTypeRequest;
import myproject.project.model.response.VehicleTypeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleTypeMapper implements Mapper<VehicleType> {

    public VehicleType to(VehicleTypeRequest request) {
        VehicleType vehicleType = new VehicleType();
        BeanUtils.copyProperties(request, vehicleType);
        return vehicleType;
    }

    public VehicleTypeResponse to(VehicleType vehicleType) {
        VehicleTypeResponse response = new VehicleTypeResponse();
        BeanUtils.copyProperties(vehicleType, response);
        return response;
    }
}
