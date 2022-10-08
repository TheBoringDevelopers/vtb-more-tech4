package ru.the_boring_developers.common.entity.vtb_api.balance.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CoinBalanceResponse {

    private BigDecimal maticAmount;
    private BigDecimal coinsAmount;
}
