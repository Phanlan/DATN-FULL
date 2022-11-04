package myproject.project.mapper;

import lombok.AllArgsConstructor;
import myproject.project.constant.SystemConstant;
import myproject.project.entity.*;
import myproject.project.exception.BusinessCode;
import myproject.project.exception.BusinessException;
import myproject.project.model.request.InvoiceRequest;
import myproject.project.model.response.InvoiceResponse;
import myproject.project.repository.CompanyRepository;
import myproject.project.repository.MonthUsedServiceRepository;
import myproject.project.repository.UsedElectricWaterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@AllArgsConstructor
public class InvoiceMapper implements Mapper<Invoice> {

    private final CompanyRepository companyRepository;
    final MonthUsedServiceRepository monthUsedServiceRepository;
    final UsedElectricWaterRepository usedElectricWaterRepository;

    private Timestamp convertStringToTimestamp(String invoiceDate){
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(invoiceDate + " 00:00:00"));
        return Timestamp.valueOf(localDateTime);
    }

    private String convertTimestampToString(Timestamp invoiceDate){
        return invoiceDate.toString().split(" ")[0];
    }

    public Invoice to(InvoiceRequest request){
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoice,request);
        Long companyId = request.getCompanyId();

        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY)
        );

            Integer month = request.getMonth();
            Integer year = request.getYear();
            List<MonthUsedService> monthUsedServiceList = monthUsedServiceRepository.findByCompanyIdAndDate(companyId, month, year);
            List<UsedElectricWater> usedEletricWaterList = usedElectricWaterRepository.findByCompanyIdAndDate(companyId, month, year);
            System.out.println(usedEletricWaterList);

            float servicePrice = 0;
            for (MonthUsedService monthUsedService : monthUsedServiceList) {
                UsedService usedService = monthUsedService.getUsedService();
                myproject.project.entity.Service service = usedService.getService();
                servicePrice += service.getPrice();
            }
            System.out.println(servicePrice);
            float waterPrice = 0;
            float electricPrice = 0;
            for (UsedElectricWater usedElectricWater : usedEletricWaterList) {
                waterPrice = (float) (usedElectricWater.getWaterNumber() * 2500);
                electricPrice = (float) (usedElectricWater.getElectricNumber() * 7000);
            }
            Integer numberOfEmployee = company.getCompanyEmployeeList().size();
            Float area = company.getArea();
            Float rentalPrice = SystemConstant.RENTAL_PRICE * area;
            servicePrice = servicePrice + servicePrice * (numberOfEmployee / 5 + (int) (area / 10)) * 5 / 100;
            invoice.setCompany(company);
            invoice.setServicePrice(servicePrice);
            invoice.setWaterPrice(waterPrice);
            invoice.setElectricPrice(electricPrice);
            invoice.setRentalPrice(rentalPrice);
            invoice.setTotal(servicePrice + rentalPrice + waterPrice + electricPrice);
            invoice.setInvoiceDate(convertStringToTimestamp(request.getInvoiceDate()));
            System.out.println(invoice);

        return invoice;
    }

    public Invoice to(Invoice invoice, InvoiceRequest request){
        BeanUtils.copyProperties(request, invoice);
        return invoice;
    }

    public InvoiceResponse to(Invoice invoice){
        InvoiceResponse response = new InvoiceResponse();
        BeanUtils.copyProperties(invoice, response);
        response.setCompanyId(invoice.getCompany().getId());
        response.setCompanyName(invoice.getCompany().getName());
        response.setInvoiceDate(convertTimestampToString(invoice.getInvoiceDate()));
        System.out.println(invoice);
        return response;
    }
}
