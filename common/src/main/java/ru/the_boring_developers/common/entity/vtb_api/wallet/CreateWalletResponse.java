package ru.the_boring_developers.common.entity.vtb_api.wallet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateWalletResponse {

    private String privateKey;
    private String publicKey;
}
