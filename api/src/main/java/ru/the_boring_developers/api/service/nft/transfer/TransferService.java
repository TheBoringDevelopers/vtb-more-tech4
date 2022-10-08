package ru.the_boring_developers.api.service.nft.transfer;

import ru.the_boring_developers.common.entity.nft.Currency;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;

import java.math.BigDecimal;

public interface TransferService {

    TransferResponse transfer(Currency currency, Long userInfoId, Long toUserInfoId, BigDecimal value);
}
