package com.danexpc.agency.helpers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class DateTimeConverter {

    public static LocalDateTime fromEpoch(Long epoch) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(epoch), ZoneId.systemDefault());
    }


    public static Long dateTimeToEpoch(LocalDateTime dateTime) {
        return Objects.nonNull(dateTime) ? dateTime.atZone(ZoneId.systemDefault()).toEpochSecond() : 0;
    }
}