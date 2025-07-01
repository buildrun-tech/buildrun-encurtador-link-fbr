package tech.buildrun.core.domain;

import tech.buildrun.core.exception.FilterException;

import java.time.LocalDate;
import java.util.Map;

import static java.util.Objects.isNull;

public record LinkFilter(
        Boolean active,
        LocalDate startCreatedAt,
        LocalDate endCreatedAt
) {

    public void validate() {
        if (!isNull(startCreatedAt) && !isNull(endCreatedAt) && startCreatedAt.isAfter(endCreatedAt)) {
            throw new FilterException(Map.of("startCreatedAt", "must be before endCreatedAt"));
        }
    }
}
