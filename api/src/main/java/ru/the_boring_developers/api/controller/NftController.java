package ru.the_boring_developers.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.the_boring_developers.api.service.nft.NftService;
import ru.the_boring_developers.api.service.nft.balance.BalanceService;
import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.entity.nft.NftBuyRequest;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/nft")
@AllArgsConstructor
public class NftController {

    private final NftService nftService;

    @GetMapping("/usual")
    public ResponseEntity<List<Nft>> findUsual() {
        return ok(nftService.findAll("USUAL"));
    }

    @GetMapping("/clothes")
    public ResponseEntity<List<Nft>> findClothes() {
        return ok(nftService.findAll("CLOTHES"));
    }

    @PostMapping("/buy")
    public ResponseEntity<TransferResponse> buy(@RequestBody NftBuyRequest request) {
        return ok(nftService.buy(request));
    }
}
