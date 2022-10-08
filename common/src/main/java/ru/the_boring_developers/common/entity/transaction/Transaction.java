package ru.the_boring_developers.common.entity.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Long id;
    private Long userIdFrom;
    private Long userIdTo;
    private String externalId;
    private String status;
    private String type;
    private String amount;
}
