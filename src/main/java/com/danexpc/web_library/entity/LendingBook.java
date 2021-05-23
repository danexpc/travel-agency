package com.danexpc.web_library.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LendingBook implements Identifiable<Integer> {

    private Integer id;

    private Book book;

    private Membership membership;

    private LendingType type;

    private LocalDateTime loanedDate;

    private LocalDateTime estimatedReturnedDate;

    private LocalDateTime realReturnedDate;

    private BigDecimal overdueFine;

    public LendingBook(Book book, Membership membership, LendingType type, LocalDateTime loanedDate,
                       LocalDateTime estimatedReturnedDate, LocalDateTime realReturnedDate, BigDecimal overdueFine) {
        this.book = book;
        this.membership = membership;
        this.type = type;
        this.loanedDate = loanedDate;
        this.estimatedReturnedDate = estimatedReturnedDate;
        this.realReturnedDate = realReturnedDate;
        this.overdueFine = overdueFine;
    }

    public LendingBook(int id, Book book, Membership membership, LendingType type, LocalDateTime loanedDate,
                       LocalDateTime estimatedReturnedDate, LocalDateTime realReturnedDate, BigDecimal overdueFine) {
        this.id = id;
        this.book = book;
        this.membership = membership;
        this.type = type;
        this.loanedDate = loanedDate;
        this.estimatedReturnedDate = estimatedReturnedDate;
        this.realReturnedDate = realReturnedDate;
        this.overdueFine = overdueFine;
    }
}
