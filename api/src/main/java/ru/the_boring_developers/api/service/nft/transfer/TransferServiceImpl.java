package ru.the_boring_developers.api.service.nft.transfer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.the_boring_developers.api.rest.vtb.VtbRestTemplate;
import ru.the_boring_developers.api.service.nft.transaction.TransactionService;
import ru.the_boring_developers.common.entity.transaction.Transaction;
import ru.the_boring_developers.common.entity.transaction.TransactionType;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferRequest;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;
import ru.the_boring_developers.common.repository.wallet.WalletRepository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final VtbRestTemplate vtbRestTemplate;
    private final WalletRepository walletRepository;
    private final TransactionService transactionService;

    private final Map<TransactionType, Function<TransferRequest, TransferResponse>> functions = Map.of(
            TransactionType.MATIC, this::transferMatic,
            TransactionType.RUBLE, this::transferRuble,
            TransactionType.NFT, this::transferNft);

    @Override
    public TransferResponse transfer(TransactionType transactionType, Long userInfoId, Long toUserInfoId, BigDecimal amount) {
        TransferResponse response =
                functions.get(transactionType)
                        .apply(
                                TransferRequest.builder()
                                        .fromPrivateKey(walletRepository.find(userInfoId).getPrivateKey())
                                        .toPublicKey(walletRepository.find(toUserInfoId).getPublicKey())
                                        .amount(amount)
                                        .build()
                        );
        transactionService.create(transactionType, amount, userInfoId, toUserInfoId, response);
        return response;
    }

    private TransferResponse transferMatic(TransferRequest transferRequest) {
        return vtbRestTemplate.transferMatic(transferRequest.getFromPrivateKey(), transferRequest.getToPublicKey(), transferRequest.getAmount());
    }

    private TransferResponse transferRuble(TransferRequest transferRequest) {
        return vtbRestTemplate.transferRuble(transferRequest.getFromPrivateKey(), transferRequest.getToPublicKey(), transferRequest.getAmount());
    }

    private TransferResponse transferNft(TransferRequest transferRequest) {
        return vtbRestTemplate.transferNft(transferRequest.getFromPrivateKey(), transferRequest.getToPublicKey(), transferRequest.getAmount().longValue());
    }
}
