package com.danexpc.web_library.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membership implements Identifiable<Integer> {

    private Integer id;

    @NonNull
    private LocalDateTime issuedDate;

    @NonNull
    private LocalDateTime expiryDate;

    @NonNull
    private User user;

    @NonNull
    private Boolean isBlocked;
}
