package ru.the_boring_developers.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.the_boring_developers.api.service.nft.NftService;
import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.entity.nft.NftBuyRequest;
import ru.the_boring_developers.common.entity.nft.NftType;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class HealthController {

    @GetMapping
    public ResponseEntity<String> findUsual() {
        return ok("The_boring_developers");
    }
}
