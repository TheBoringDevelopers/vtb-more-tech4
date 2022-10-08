package ru.the_boring_developers.common.entity.vtb_api.transfer.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class NftGenerateRequest {

    private String toPublicKey;
    private String uri;
    private Long nftCount;
}
