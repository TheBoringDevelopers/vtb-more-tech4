package ru.the_boring_developers.api.rest.vtb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.the_boring_developers.api.rest.vtb.properties.VtbProperties;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.balance.nft.NftBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.transaction.TransactionStatusResponse;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferRequest;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;
import ru.the_boring_developers.common.entity.vtb_api.transfer.nft.NftTransferRequest;
import ru.the_boring_developers.common.entity.vtb_api.wallet.CreateWalletResponse;
import ru.the_boring_developers.common.exception.DomainException;

import java.math.BigDecimal;
import java.util.Collections;

@Slf4j
@ConditionalOnBean(name = "vtbRestTemplate")
@RequiredArgsConstructor
public class VtbRestTemplate {

    private final VtbProperties vtbProperties;
    private final RestTemplate restTemplate;

    public CreateWalletResponse createWallet() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            return restTemplate.postForObject(
                    vtbProperties.getBaseUrl() + "/v1/wallets/new",
                    entity,
                    CreateWalletResponse.class
            );
        } catch (RestClientException e) {
            log.error("Ошибка запроса создания кошелька: " + e.getMessage());
            throw new DomainException("Ошибка запроса");
        }
    }

    public TransferResponse transferMatic(String fromPrivateKey, String toPublicKey, BigDecimal amount) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            return restTemplate.postForObject(
                    vtbProperties.getBaseUrl() + "/v1/transfers/matic",
                    new HttpEntity<>(new TransferRequest(fromPrivateKey, toPublicKey, amount), headers),
                    TransferResponse.class
            );
        } catch (RestClientException e) {
            log.error("Ошибка перевода matic: " + e.getMessage());
            throw new DomainException("Ошибка запроса");
        }
    }

    public TransferResponse transferRuble(String fromPrivateKey, String toPublicKey, BigDecimal amount) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            return restTemplate.postForObject(
                    vtbProperties.getBaseUrl() + "/v1/transfers/ruble",
                    new HttpEntity<>(new TransferRequest(fromPrivateKey, toPublicKey, amount), headers),
                    TransferResponse.class
            );
        } catch (RestClientException e) {
            log.error("Ошибка перевода ruble: " + e.getMessage());
            throw new DomainException("Ошибка запроса");
        }
    }

    public TransferResponse transferNft(String fromPrivateKey, String toPublicKey, Long tokenId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            return restTemplate.postForObject(
                    vtbProperties.getBaseUrl() + "/v1/transfers/nft",
                    new HttpEntity<>(new NftTransferRequest(fromPrivateKey, toPublicKey, tokenId), headers),
                    TransferResponse.class
            );
        } catch (RestClientException e) {
            log.error("Ошибка перевода nft: " + e.getMessage());
            throw new DomainException("Ошибка запроса");
        }
    }

    public CoinBalanceResponse balanceCoins(String publicKey) {
        try {
            return restTemplate.getForObject(
                    String.format(vtbProperties.getBaseUrl() + "/v1/wallets/%s/balance", publicKey),
                    CoinBalanceResponse.class
            );
        } catch (RestClientException e) {
            log.error("Ошибка баланса токенов: " + e.getMessage());
            throw new DomainException("Ошибка запроса");
        }
    }

    public NftBalanceResponse balanceNft(String publicKey) {
        try {
            return restTemplate.getForObject(
                    String.format(vtbProperties.getBaseUrl() + "/v1/wallets/%s/nft/balance", publicKey),
                    NftBalanceResponse.class
            );
        } catch (RestClientException e) {
            log.error("Ошибка баланса токенов: " + e.getMessage());
            throw new DomainException("Ошибка запроса");
        }
    }

    public TransactionStatusResponse getTransactionStatus(String hash) {
        try {
            return restTemplate.getForObject(
                    String.format(vtbProperties.getBaseUrl() + "/v1/transfers/status/%s", hash),
                    TransactionStatusResponse.class
            );
        } catch (RestClientException e) {
            log.error("Ошибка проверки статуса: " + e.getMessage());
            throw new DomainException("Ошибка запроса");
        }
    }
}
