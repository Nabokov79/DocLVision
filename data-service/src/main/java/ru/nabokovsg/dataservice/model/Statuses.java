package ru.nabokovsg.dataservice.model;

import java.util.Optional;

public enum Statuses {

    WAITING,
    WORK,
    COMPLETED,
    PRINT,
    PASSED;

    public static Optional<Statuses> from(String stringStatus) {
        for (Statuses status : values()) {
            if (status.name().equalsIgnoreCase(stringStatus)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
