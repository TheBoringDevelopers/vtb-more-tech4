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
public class Nft {
    private String uri;
    private String name;
    private String imageUrl;
    private BigDecimal maticPrice;
    private Double rublePrice;
    private String type;
    private List<Long> tokens;
}
