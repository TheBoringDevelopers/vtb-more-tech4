package ru.the_boring_developers.common.entity.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class NftBuyRequest {
    private String uri;
    private Long userId;
    private Currency currency;
}
