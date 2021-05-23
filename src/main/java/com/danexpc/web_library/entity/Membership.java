package com.danexpc.web_library.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Membership implements Identifiable<Integer> {

    private Integer id;

    private LocalDateTime issuedDate;

    private LocalDateTime expiryDate;

    private User user;

    private boolean isBlocked;

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
}
