package myproject.project.mapper;

import lombok.AllArgsConstructor;
import myproject.project.entity.Company;
import myproject.project.entity.CompanyEmployee;
import myproject.project.entity.Vehicle;
import myproject.project.entity.VehicleType;
import myproject.project.exception.BusinessCode;
import myproject.project.exception.BusinessException;
import myproject.project.model.request.VehicleRequest;
import myproject.project.model.response.VehicleResponse;
import myproject.project.repository.CompanyEmployeeRepository;
import myproject.project.repository.CompanyRepository;
import myproject.project.repository.VehicleTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleMapper implements Mapper<Vehicle> {

    private final CompanyRepository companyRepository;

    private final CompanyEmployeeRepository companyEmployeeRepository;

    private final VehicleTypeRepository vehicleTypeRepository;

    public Vehicle to(VehicleRequest request){
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(request,vehicle);
        Long companyId = request.getCompanyId();
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );
        System.out.println(company);
        Long companyEmployeeId = request.getCompanyEmployeeId();
        CompanyEmployee companyEmployee = companyEmployeeRepository.findById(companyEmployeeId).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );
        System.out.println(companyEmployee);
        Long vehicleTypeId = request.getVehicleTypeId();
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );

        vehicle.setCompany((company));
        vehicle.setCompanyEmployee(companyEmployee);
        vehicle.setVehicleType(vehicleType);
        return vehicle;
    }

    public Vehicle to(Vehicle vehicle, VehicleRequest request){
        BeanUtils.copyProperties(request, vehicle);
        Long companyId = request.getCompanyId();
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );
        System.out.println(company);
        Long companyEmployeeId = request.getCompanyEmployeeId();
        CompanyEmployee companyEmployee = companyEmployeeRepository.findById(companyEmployeeId).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );
        System.out.println(companyEmployee);
        Long vehicleTypeId = request.getVehicleTypeId();
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );

        vehicle.setCompany((company));
        vehicle.setCompanyEmployee(companyEmployee);
        vehicle.setVehicleType(vehicleType);
        return vehicle;
    }

    public VehicleResponse to(Vehicle vehicle){
        VehicleResponse response = new VehicleResponse();
        BeanUtils.copyProperties(vehicle, response);

        response.setId(vehicle.getId());
        response.setCompanyId(vehicle.getCompany().getId());
        response.setCompanyName(vehicle.getCompany().getName());
        response.setCompanyEmployeeId(vehicle.getCompanyEmployee().getId());
        response.setCompanyEmployeeName(vehicle.getCompanyEmployee().getName());
        response.setVehicleTypeDescription(vehicle.getVehicleType().getDescription());
        response.setVehicleTypeId(vehicle.getVehicleType().getId());
        return response;
    }
}
