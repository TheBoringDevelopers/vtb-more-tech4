package ru.the_boring_developers.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.the_boring_developers.api.service.nft.balance.BalanceService;
import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {

    private final BalanceService balanceService;

    @GetMapping("/nft")
    public ResponseEntity<List<Nft>> balanceNft(@RequestParam Long userId) {
        return ok(balanceService.balanceNft(userId));
    }

    @GetMapping("/coin")
    public ResponseEntity<CoinBalanceResponse> balanceCoin(@RequestParam Long userId) {
        return ok(balanceService.balanceCoin(userId));
    }
}
