package ru.the_boring_developers.common.entity.vtb_api.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ru.the_boring_developers.common.entity.vtb_api.balance.nft.NftBalance;

import java.util.List;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStatusResponse {

    private String status;
}
