package br.com.doardin.myexpenses.application.paymentmethod.service;

import org.springframework.stereotype.Service;

import br.com.doardin.myexpenses.application.paymentmethod.dto.PostPaymentMethod;
import br.com.doardin.myexpenses.application.paymentmethod.dto.ResponsePaymentMethodDto;
import br.com.doardin.myexpenses.domain.paymentmethod.PaymentMethodRepository;
import br.com.doardin.myexpenses.mapper.PaymentMethodMapper;
import br.com.doardin.myexpenses.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentMethodAppService {

    private final PaymentMethodMapper mapper;
    private final CurrentUserUtil currentUserUtil;
    private final PaymentMethodRepository repository;

    public ResponsePaymentMethodDto createPaymentMethod(PostPaymentMethod dto) {
        var user = currentUserUtil.getUserFromSecurityContextHolder();
        var paymentMethod = mapper.toPaymentMethod(dto, user);
        paymentMethod = repository.save(paymentMethod);
        return mapper.toResponsePaymentMethod(paymentMethod);
    }

}
