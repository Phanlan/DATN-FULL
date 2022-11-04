package myproject.project.model.response;

import lombok.Data;

@Data
public class MonthStatCompanyResponse {
    private CompanyResponse companyResponse;
    private Float rentalPrice;
    private Float servicePrice;
    private Float totalPrice;
    private Float electricPrice;
    private Float waterPrice;

}
