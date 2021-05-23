package com.danexpc.web_library.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LendingBook implements Identifiable<Integer> {

    private Integer id;

    @NonNull
    private Book book;

    @NonNull
    private Membership membership;

    @NonNull
    private LendingType type;

    @NonNull
    private LocalDateTime loanedDate;

    @NonNull
    private LocalDateTime estimatedReturnedDate;

    @NonNull
    private LocalDateTime realReturnedDate;

    @NonNull
    private BigDecimal overdueFine;
}
