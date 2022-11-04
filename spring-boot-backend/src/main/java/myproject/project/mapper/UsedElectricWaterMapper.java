package myproject.project.mapper;

import myproject.project.entity.Company;
import myproject.project.entity.UsedElectricWater;
import myproject.project.model.request.UsedElectricWaterRequest;
import myproject.project.model.response.UsedElectricWaterResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UsedElectricWaterMapper implements Mapper<UsedElectricWater> {

    private Timestamp convertStringToTimestamp(String month){
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(month + " 00:00:00"));
        return Timestamp.valueOf(localDateTime);
    }

    private String convertTimestampToString(Timestamp month){
        return month.toString().split(" ")[0];}

    public UsedElectricWaterResponse to(UsedElectricWater usedElectricWater){
        UsedElectricWaterResponse response = new UsedElectricWaterResponse();
        BeanUtils.copyProperties(usedElectricWater, response);
        response.setMonth(convertTimestampToString(usedElectricWater.getMonth()));
        response.setCompany_id(usedElectricWater.getCompany().getId());
        response.setCompanyName(usedElectricWater.getCompany().getName());
        return response;
    }

    public UsedElectricWater to(UsedElectricWaterRequest request){
        UsedElectricWater usedElectricWater = new UsedElectricWater();
        Company company = new Company();
        company.setId(request.getCompany_id());
        BeanUtils.copyProperties(request,usedElectricWater);
        usedElectricWater.setMonth(convertStringToTimestamp(request.getMonth()));
        usedElectricWater.setCompany(company);
        return usedElectricWater;
    }
}
