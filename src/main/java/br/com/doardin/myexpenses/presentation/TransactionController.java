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
import br.com.doardin.myexpenses.application.transaction.dto.PostTransactionDto;
import br.com.doardin.myexpenses.application.transaction.dto.ResponseTransactionDto;
import br.com.doardin.myexpenses.application.transaction.service.TransactionAppService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionAppService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseTransactionDto createTransaction(@RequestBody @Valid PostTransactionDto dto) {
        return service.createTransaction(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseContentPaginatedDto getTransactions(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getTransactions(page, size);
    }

}
