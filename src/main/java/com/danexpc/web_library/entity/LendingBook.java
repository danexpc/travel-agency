package com.danexpc.web_library.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class LendingBook extends BaseEntity {

    private Book book;

    private Membership membership;

    private LendingType type;

    private LocalDateTime loanedDate;

    private LocalDateTime estimatedReturnedDate;

    private LocalDateTime realReturnedDate;

    private BigDecimal overdueFine;

    public LendingBook() {
    }

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
        super(id);
        this.book = book;
        this.membership = membership;
        this.type = type;
        this.loanedDate = loanedDate;
        this.estimatedReturnedDate = estimatedReturnedDate;
        this.realReturnedDate = realReturnedDate;
        this.overdueFine = overdueFine;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public LendingType getType() {
        return type;
    }

    public void setType(LendingType type) {
        this.type = type;
    }

    public LocalDateTime getLoanedDate() {
        return loanedDate;
    }

    public void setLoanedDate(LocalDateTime loanedDate) {
        this.loanedDate = loanedDate;
    }

    public LocalDateTime getEstimatedReturnedDate() {
        return estimatedReturnedDate;
    }

    public void setEstimatedReturnedDate(LocalDateTime estimatedReturnedDate) {
        this.estimatedReturnedDate = estimatedReturnedDate;
    }

    public LocalDateTime getRealReturnedDate() {
        return realReturnedDate;
    }

    public void setRealReturnedDate(LocalDateTime realReturnedDate) {
        this.realReturnedDate = realReturnedDate;
    }

    public BigDecimal getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(BigDecimal overdueFine) {
        this.overdueFine = overdueFine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        var that = (LendingBook) o;
        return Objects.equals(book, that.book) && Objects.equals(membership, that.membership)
                && type == that.type && Objects.equals(loanedDate, that.loanedDate)
                && Objects.equals(estimatedReturnedDate, that.estimatedReturnedDate)
                && Objects.equals(realReturnedDate, that.realReturnedDate)
                && Objects.equals(overdueFine, that.overdueFine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), book, membership, type,
                loanedDate, estimatedReturnedDate, realReturnedDate, overdueFine);
    }

    @Override
    public String toString() {
        return "LendingBook{" +
                "id=" + getId() +
                ", book=" + book +
                ", membership=" + membership +
                ", type=" + type +
                ", loanedDate=" + loanedDate +
                ", estimatedReturnedDate=" + estimatedReturnedDate +
                ", realReturnedDate=" + realReturnedDate +
                ", overdueFine=" + overdueFine +
                '}';
    }
}
