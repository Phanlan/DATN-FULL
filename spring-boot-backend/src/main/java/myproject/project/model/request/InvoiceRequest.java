package myproject.project.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InvoiceRequest {
    @NotNull
    private Long companyId;
    private Integer month;
    private Integer year;
    private String invoiceDate;
}
