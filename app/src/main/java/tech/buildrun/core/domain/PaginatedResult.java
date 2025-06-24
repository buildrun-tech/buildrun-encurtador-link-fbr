package tech.buildrun.core.domain;

import java.util.List;

public record PaginatedResult<T>(
        List<T> items,
        String nextToken,
        boolean hasMore
) {
}
