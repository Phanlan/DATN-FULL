package myproject.project.model.response;

import lombok.Data;

@Data
public class InvoiceResponse {
    private Long companyId;
    private String companyName;
    private Long id;
    private Float total;
    private String month;
    private String invoiceDate;
    private Float rentalPrice;
    private Float servicePrice;
    private Float totalPrice;
    private Float electricPrice;
    private Float waterPrice;
}
