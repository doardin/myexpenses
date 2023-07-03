package br.com.doardin.myexpenses.application.paymentmethod.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.doardin.myexpenses.application.general.dto.ResponseContentPaginatedDto;
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

    public ResponseContentPaginatedDto getPaymentMethods(int page, int size) {
        var pageable = PageRequest.of(page, size, Direction.DESC, "createdAt");
        var user = currentUserUtil.getUserFromSecurityContextHolder();
        var paymentMethods = repository.findAllByUser(user, pageable);

        return ResponseContentPaginatedDto.builder()
                .totalPages(paymentMethods.getTotalPages())
                .content(paymentMethods.getContent().stream().map(mapper::toResponsePaymentMethod)
                        .collect(Collectors.toList()))
                .build();
    }

}
