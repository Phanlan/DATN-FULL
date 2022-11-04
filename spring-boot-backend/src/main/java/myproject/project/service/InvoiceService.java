package myproject.project.service;

import myproject.project.model.request.InvoiceRequest;
import myproject.project.model.response.InvoiceResponse;

import java.util.List;

public interface InvoiceService {
    InvoiceResponse save(InvoiceRequest request);
    List<InvoiceResponse> getAllInvoice();
}
