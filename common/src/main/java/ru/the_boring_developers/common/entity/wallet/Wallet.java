package ru.the_boring_developers.common.entity.wallet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private Long id;
    private String publicKey;
    private String privateKey;
    private Long userInfoId;
}
