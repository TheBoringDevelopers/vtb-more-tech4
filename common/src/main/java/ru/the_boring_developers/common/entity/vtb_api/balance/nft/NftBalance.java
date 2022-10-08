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
public class NftBalance {

    private String URI;
    /** массив NFT. Т.е. 5,3,4,6 - уникальные идентификаторы отдельного NFT в NFT-коллекции */
    private List<Long> tokens;
}
