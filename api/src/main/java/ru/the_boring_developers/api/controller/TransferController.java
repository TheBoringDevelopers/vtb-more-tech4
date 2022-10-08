package ru.the_boring_developers.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.the_boring_developers.api.service.nft.transfer.TransferService;
import ru.the_boring_developers.common.entity.nft.Currency;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;

import java.math.BigDecimal;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/transfer")
@AllArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/ruble")
    public ResponseEntity<TransferResponse> transferRuble(@RequestParam Long userId,
                                                          @RequestParam Long toUserId,
                                                          @RequestParam BigDecimal amount) {
        return ok(transferService.transfer(Currency.RUBLE, userId, toUserId, amount));
    }

    @PostMapping("/matic")
    public ResponseEntity<TransferResponse> transferMatic(@RequestParam Long userId,
                                                          @RequestParam Long toUserId,
                                                          @RequestParam BigDecimal amount) {
        return ok(transferService.transfer(Currency.MATIC, userId, toUserId, amount));
    }

    @PostMapping("/nft")
    public ResponseEntity<TransferResponse> transferNft(@RequestParam Long userId,
                                                        @RequestParam Long toUserId,
                                                        @RequestParam Long tokenId) {
        return ok(transferService.transfer(Currency.NFT, userId, toUserId, BigDecimal.valueOf(tokenId)));
    }
}
