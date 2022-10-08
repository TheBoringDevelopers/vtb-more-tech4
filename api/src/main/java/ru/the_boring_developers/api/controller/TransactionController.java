package ru.the_boring_developers.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.the_boring_developers.common.entity.transaction.Transaction;
import ru.the_boring_developers.api.service.nft.transaction.TransactionService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> find(@RequestParam Long userId) {
        return ok(transactionService.find(userId));
    }
}
