package com.danexpc.web_library.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Membership implements Identifiable<Integer> {

    private Integer id;

    private LocalDateTime issuedDate;

    private LocalDateTime expiryDate;

    private User user;

    private boolean isBlocked;

    public Membership() {
    }

    public Membership(LocalDateTime issuedDate, LocalDateTime expiryDate, User user, boolean isBlocked) {
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
        this.user = user;
        this.isBlocked = isBlocked;
    }

    public Membership(int id, LocalDateTime issuedDate, LocalDateTime expiryDate, User user, boolean isBlocked) {
        this.id = id;
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
        this.user = user;
        this.isBlocked = isBlocked;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDateTime issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (Membership) o;
        return id.equals(that.id)
                && isBlocked == that.isBlocked
                && Objects.equals(issuedDate, that.issuedDate)
                && Objects.equals(expiryDate, that.expiryDate)
                && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issuedDate, expiryDate, user, isBlocked);
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", issuedDate=" + issuedDate +
                ", expiryDate=" + expiryDate +
                ", user=" + user +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
