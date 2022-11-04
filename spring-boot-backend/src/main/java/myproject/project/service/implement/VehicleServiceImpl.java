package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import myproject.project.entity.Vehicle;
import myproject.project.exception.BusinessCode;
import myproject.project.exception.BusinessException;
import myproject.project.mapper.VehicleMapper;
import myproject.project.model.request.VehicleRequest;
import myproject.project.model.response.VehicleResponse;
import myproject.project.repository.VehicleRepository;
import myproject.project.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleResponse save(VehicleRequest request) {
        Vehicle vehicle = vehicleRepository.saveAndFlush(vehicleMapper.to(request));
        return vehicleMapper.to(vehicle);
    }

    @Override
    public List<VehicleResponse> getAllVehicle() {
        List<Vehicle> vehicleList = vehicleRepository.findAllByIsDeleted(false);
        return vehicleList.stream().map(vehicleMapper::to).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VehicleResponse updateById(Long id, VehicleRequest request) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );
        return vehicleMapper.to(vehicleRepository.saveAndFlush(vehicleMapper.to(vehicle,request)));
    }
}
