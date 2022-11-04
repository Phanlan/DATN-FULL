package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import myproject.project.entity.*;
import myproject.project.exception.BusinessCode;
import myproject.project.exception.BusinessException;
import myproject.project.mapper.CompanyMapper;
import myproject.project.model.request.CompanyRequest;
import myproject.project.model.response.CompanyDetailResponse;
import myproject.project.model.response.CompanyResponse;
import myproject.project.repository.*;
import myproject.project.service.CompanyService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyEmployeeRepository companyEmployeeRepository;
    private final UsedServiceRepository usedServiceRepository;
    private final ServiceRepository serviceRepository;
    private final MonthUsedServiceRepository monthUsedServiceRepository;

    private final UsedElectricWaterRepository usedElectricWaterRepository;

    private final UsedInfrastructureRepository usedInfrastructureRepository;

    private final CompanyMapper companyMapper;
    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public CompanyResponse save(CompanyRequest request) {
        Company company = companyRepository.saveAndFlush(companyMapper.to(request));
        serviceRequired(company);
        return companyMapper.to(company);
    }

    @Override
    @Transactional
    public CompanyResponse updateById(Long id, CompanyRequest request) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );
        return companyMapper.to(companyRepository.saveAndFlush(companyMapper.to(company,request)));
    }

    @Override
    public CompanyDetailResponse findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );
        List<UsedService> usedServiceList = usedServiceRepository.findByIsDeletedAndCompanyId(false,id);
        List<CompanyEmployee> companyEmployeeList = companyEmployeeRepository.findCompanyEmployeeByIsDeletedAndCompanyId(false,id);
        List<UsedElectricWater> usedElectricWaterList = usedElectricWaterRepository.findUsedElectricWaterByIsDeletedAndCompanyId(false,id);
        List<UsedInfrastructure> usedInfrastructureList = usedInfrastructureRepository.findByIsDeletedAndCompanyId(false,id);
        List<Vehicle> vehicleList = vehicleRepository.findByIsDeletedAndCompanyId(false,id);
        company.setCompanyEmployeeList(companyEmployeeList);
        company.setUsedServiceList(usedServiceList);
        company.setUsedElectricWaterList(usedElectricWaterList);
        company.setUsedInfrastructureList(usedInfrastructureList);
        company.setVehicleList(vehicleList);
        return companyMapper.toDetail(company);
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );
        company.setIsDeleted(true);
        companyRepository.saveAndFlush(company);
        List<CompanyEmployee> companyEmployeeList = companyEmployeeRepository.findCompanyEmployeeByIsDeletedAndCompanyId(false,company.getId());
        List<UsedService> usedServiceList = usedServiceRepository.findByIsDeletedAndCompanyId(false,company.getId());
        for(CompanyEmployee companyEmployee: companyEmployeeList){
            companyEmployee.setIsDeleted(true);
            companyEmployeeRepository.saveAndFlush(companyEmployee);
        }
        for(UsedService usedService: usedServiceList){
            usedService.setIsDeleted(true);
            usedServiceRepository.saveAndFlush(usedService);
        }
        return "Deleted";
    }


    @Override
    public List<CompanyResponse> getAllCompany() {
        List<Company> companyList = companyRepository.findAllByIsDeleted(false);
        companyList.stream().forEach((company) ->{
            company.setCompanyEmployeeList(companyEmployeeRepository.findCompanyEmployeeByIsDeletedAndCompanyId(false, company.getId()));
        });
        return companyList.stream().map(companyMapper::to).collect(Collectors.toList());
    }

    @Override
    public List<CompanyResponse> findCompanyByNameLike(String name) {
        String searchName= "%"+name+"%";
        List<Company> companyList = companyRepository.findCompanyByIsDeletedAndNameLike(false, searchName);
        return companyList.stream().map(companyMapper::to).collect(Collectors.toList());
    }


    private void serviceRequired(Company company){
        UsedService usedService = new UsedService();
        usedService.setCompany(company);
        usedService.setStartDate(new Timestamp(System.currentTimeMillis()));

        myproject.project.entity.Service service = serviceRepository.findCleanedServiceByActiveIs(true).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CURRENT_SERVICE)
        );
        usedService.setService(service);
        usedService = usedServiceRepository.saveAndFlush(usedService);
        MonthUsedService monthUsedService = new MonthUsedService();
        Date toDate = Date.valueOf(LocalDate.now().withDayOfMonth(1));
        monthUsedService.setFromDate(new Timestamp(System.currentTimeMillis()));
        monthUsedService.setUsedService(usedService);
        monthUsedService.setToDate(new Timestamp(DateUtils.addMonths(toDate,1).getTime()));
        monthUsedServiceRepository.saveAndFlush(monthUsedService);


        usedService = new UsedService();
        usedService.setCompany(company);
        usedService.setStartDate(new Timestamp(System.currentTimeMillis()));
        service = serviceRepository.findProtectedServiceByActiveIs(true).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CURRENT_SERVICE)
        );
        usedService.setService(service);
        usedService = usedServiceRepository.saveAndFlush(usedService);

        monthUsedService = new MonthUsedService();
        monthUsedService.setFromDate(new Timestamp(System.currentTimeMillis()));
        monthUsedService.setUsedService(usedService);
        monthUsedService.setToDate(new Timestamp(DateUtils.addMonths(toDate,1).getTime()));
        monthUsedServiceRepository.saveAndFlush(monthUsedService);
    }
}
