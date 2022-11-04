package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import myproject.project.constant.SystemConstant;
import myproject.project.entity.*;
import myproject.project.mapper.CompanyMapper;
import myproject.project.model.response.MonthStatCompanyResponse;
import myproject.project.repository.CompanyRepository;
import myproject.project.repository.MonthUsedServiceRepository;
import myproject.project.repository.UsedElectricWaterRepository;
import myproject.project.service.MonthStatCompany;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class MonthStatCompanyImpl implements MonthStatCompany {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final MonthUsedServiceRepository monthUsedServiceRepository;

    private final UsedElectricWaterRepository usedElectricWaterRepository;

    @Override
    public List<MonthStatCompanyResponse> viewStatistic(int month, int year) {
        List<MonthStatCompanyResponse> monthStatCompanyResponses = new ArrayList<>();
        List<Company> companyList = companyRepository.findAllByIsDeleted(false);
        for (Company company: companyList) {
            MonthStatCompanyResponse response = new MonthStatCompanyResponse();
            response.setCompanyResponse(companyMapper.to(company));
            Long companyId = company.getId();
            List<MonthUsedService> monthUsedServiceList = monthUsedServiceRepository.findByCompanyIdAndDate(companyId, month, year);
            List<UsedElectricWater> usedEletricWaterList = usedElectricWaterRepository.findByCompanyIdAndDate(companyId, month, year);
            System.out.println(usedEletricWaterList);
            if(!monthUsedServiceList.isEmpty()) {
                float servicePrice = 0;
                for (MonthUsedService monthUsedService : monthUsedServiceList) {
                    UsedService usedService = monthUsedService.getUsedService();
                    Service service = usedService.getService();
                    servicePrice += service.getPrice();
                }
                float waterPrice = 0;
                float electricPrice = 0;
                for (UsedElectricWater usedElectricWater: usedEletricWaterList){
                    waterPrice = (float) (usedElectricWater.getWaterNumber() * 7000);
                    electricPrice = (float) (usedElectricWater.getElectricNumber() * 2500);
                }
                Long numberOfEmployee = response.getCompanyResponse().getNumberOfEmployee();
                servicePrice = servicePrice + servicePrice * (numberOfEmployee / 5 + (int) (company.getArea() / 10)) * 5 / 100;
                response.setServicePrice(servicePrice);
                response.setWaterPrice(waterPrice);
                response.setElectricPrice(electricPrice);
                response.setRentalPrice(SystemConstant.RENTAL_PRICE * company.getArea());
                response.setTotalPrice(servicePrice + response.getRentalPrice() + waterPrice + electricPrice);
                monthStatCompanyResponses.add(response);
            }
            System.out.println(response);
        }
        monthStatCompanyResponses = monthStatCompanyResponses.stream().sorted(Comparator.comparing(MonthStatCompanyResponse::getTotalPrice).reversed())
                .collect(Collectors.toList());
        return monthStatCompanyResponses;
    }

    private Timestamp convertStringToTimestamp(String dateOfBirth) {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(dateOfBirth + " 00:00:00"));
        return Timestamp.valueOf(localDateTime);
    }

    private String convertTimestampToString(Timestamp dateOfBirth) {
        return dateOfBirth.toString().split(" ")[0];
    }
}
