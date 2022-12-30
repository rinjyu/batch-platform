package com.spring.batch.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    private String itemId;
    private String uitemId;
    private String salestrNo;
    private Integer baseInvQty;
    private Integer usablInvQty;
    private String invEffDivCd;
    private String regId;
    private LocalDateTime regDts;
    private String modId;
    private LocalDateTime modDts;
}
