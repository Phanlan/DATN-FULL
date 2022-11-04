package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import myproject.project.entity.VehicleType;
import myproject.project.mapper.VehicleTypeMapper;
import myproject.project.model.response.VehicleTypeResponse;
import myproject.project.repository.VehicleTypeRepository;
import myproject.project.service.VehicleTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {
    private VehicleTypeRepository vehicleTypeRepository;
    private VehicleTypeMapper mapper;

    @Override
    public List<VehicleTypeResponse> getAllVehicleType() {

        List<VehicleType> vehicleTypeList = vehicleTypeRepository.findAllByIsDeleted(false);
        return vehicleTypeList.stream().map(mapper::to).collect(Collectors.toList());
    }
}
