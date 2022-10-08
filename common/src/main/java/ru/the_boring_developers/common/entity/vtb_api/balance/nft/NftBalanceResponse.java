package ru.the_boring_developers.common.entity.vtb_api.balance.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class NftBalanceResponse {

    private List<NftBalance> balance;
}
