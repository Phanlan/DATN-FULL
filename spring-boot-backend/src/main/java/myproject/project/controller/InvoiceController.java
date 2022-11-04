package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.InvoiceRequest;
import myproject.project.model.response.BaseResponse;
import myproject.project.model.response.InvoiceResponse;
import myproject.project.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("public-api/v1.0.0/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping("/create")
    BaseResponse<InvoiceResponse> createInvoice(@RequestBody @Valid InvoiceRequest request){
        return BaseResponse.ofSuccess(invoiceService.save(request));
    }

    @GetMapping("/list")
    BaseResponse<List<InvoiceResponse>> getAllInVoice(){
        return BaseResponse.ofSuccess(invoiceService.getAllInvoice());
    }
}
