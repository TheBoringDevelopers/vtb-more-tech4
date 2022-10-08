package ru.the_boring_developers.common.entity.vtb_api.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

    private String fromPrivateKey;
    private String toPublicKey;
    private BigDecimal amount;
}
