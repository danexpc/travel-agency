package com.danexpc.web_library.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Membership extends BaseEntity {

    private static final long serialVersionUID = -3493504014636653623L;

    private Timestamp issuedDate;

    private Timestamp expiryDate;

    private User user;

    private boolean isBlocked;

    public Membership() {
    }

    public Membership(Timestamp issuedDate, Timestamp expiryDate, User user, boolean isBlocked) {
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
        this.user = user;
        this.isBlocked = isBlocked;
    }

    public Membership(int id, Timestamp issuedDate, Timestamp expiryDate, User user, boolean isBlocked) {
        super(id);
        this.issuedDate = issuedDate;
        this.expiryDate = expiryDate;
        this.user = user;
        this.isBlocked = isBlocked;
    }

    public Timestamp getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Timestamp issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
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
        if (!super.equals(o)) return false;
        var that = (Membership) o;
        return isBlocked == that.isBlocked && Objects.equals(issuedDate, that.issuedDate)
                && Objects.equals(expiryDate, that.expiryDate) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), issuedDate, expiryDate, user, isBlocked);
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + getId() +
                ", issuedDate=" + issuedDate +
                ", expiryDate=" + expiryDate +
                ", user=" + user +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
