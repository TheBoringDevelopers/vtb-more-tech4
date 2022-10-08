package ru.the_boring_developers.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.the_boring_developers.api.service.user.NftService;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.balance.nft.NftBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;

import java.math.BigDecimal;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/transfer")
@AllArgsConstructor
public class TransferController {

    private final NftService nftService;

    @PostMapping("/ruble")
    public ResponseEntity<TransferResponse> transferRuble(@RequestHeader Long userId,
                                                          @RequestParam Long toUserId,
                                                          @RequestParam BigDecimal amount) {
        return ok(nftService.transferRuble(userId, toUserId, amount));
    }

    @PostMapping("/matic")
    public ResponseEntity<TransferResponse> transferMatic(@RequestHeader Long userId,
                                                          @RequestParam Long toUserId,
                                                          @RequestParam BigDecimal amount) {
        return ok(nftService.transferMatic(userId, toUserId, amount));
    }

    @PostMapping("/nft")
    public ResponseEntity<TransferResponse> transferNft(@RequestHeader Long userId,
                                                        @RequestParam Long toUserId,
                                                        @RequestParam Long tokenId) {
        return ok(nftService.transferNft(userId, toUserId, tokenId));
    }
}
