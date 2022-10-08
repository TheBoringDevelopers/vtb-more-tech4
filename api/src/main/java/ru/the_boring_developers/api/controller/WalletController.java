package ru.the_boring_developers.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.the_boring_developers.api.service.user.NftService;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.balance.nft.NftBalanceResponse;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {

    private final NftService nftService;

    @GetMapping("/nft")
    public ResponseEntity<NftBalanceResponse> balanceNft(@RequestHeader Long userId) {
        return ok(nftService.balanceNft(userId));
    }

    @GetMapping("/coin")
    public ResponseEntity<CoinBalanceResponse> balanceCoin(@RequestHeader Long userId) {
        return ok(nftService.balanceCoin(userId));
    }
}
