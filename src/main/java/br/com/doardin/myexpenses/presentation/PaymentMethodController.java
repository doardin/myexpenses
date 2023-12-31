package br.com.doardin.myexpenses.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.doardin.myexpenses.application.general.dto.ResponseContentPaginatedDto;
import br.com.doardin.myexpenses.application.paymentmethod.dto.PostPaymentMethod;
import br.com.doardin.myexpenses.application.paymentmethod.dto.ResponsePaymentMethodDto;
import br.com.doardin.myexpenses.application.paymentmethod.service.PaymentMethodAppService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodAppService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponsePaymentMethodDto createPaymentMethod(@RequestBody @Valid PostPaymentMethod dto) {
        return service.createPaymentMethod(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseContentPaginatedDto getPaymentMethods(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getPaymentMethods(page, size);
    }

}
