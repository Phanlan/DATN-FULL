package myproject.project.service.implement;

import lombok.AllArgsConstructor;
import myproject.project.entity.Invoice;
import myproject.project.mapper.CompanyMapper;
import myproject.project.mapper.InvoiceMapper;
import myproject.project.model.request.InvoiceRequest;
import myproject.project.model.response.InvoiceResponse;
import myproject.project.repository.CompanyRepository;
import myproject.project.repository.InvoiceRepository;
import myproject.project.repository.MonthUsedServiceRepository;
import myproject.project.repository.UsedElectricWaterRepository;
import myproject.project.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final MonthUsedServiceRepository monthUsedServiceRepository;
    private final UsedElectricWaterRepository usedElectricWaterRepository;

//    @Override
//    public InvoiceResponse save(InvoiceRequest request) {
//        Invoice invoice = invoiceRepository.saveAndFlush(invoiceMapper.to(request));
//        return invoiceMapper.to(invoice);
//    }

    @Override
    public InvoiceResponse save(InvoiceRequest request) {
        Invoice invoice = invoiceRepository.saveAndFlush(invoiceMapper.to(request));
        return invoiceMapper.to(invoice);
    }


    @Override
    public List<InvoiceResponse> getAllInvoice() {
        List<Invoice> invoiceList = invoiceRepository.findAllByIsDeleted(false);
        System.out.println(invoiceList);
        return invoiceList.stream().map(invoiceMapper::to).collect(Collectors.toList());
    }
}

