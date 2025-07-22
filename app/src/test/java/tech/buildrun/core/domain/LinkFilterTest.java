package tech.buildrun.core.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.buildrun.core.exception.FilterException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinkFilterTest {


    @Nested
    class validate {


        @Test
        void shouldNotThrowFilterExceptionWhenStartCreatedAtIsNull() {
            // Arrange
            LocalDate endCreatedAt = LocalDate.now().plusDays(3);

            // Act & Assert
            var linkFilter = new LinkFilter(false, null, endCreatedAt);
            assertDoesNotThrow(linkFilter::validate);
        }

        @Test
        void shouldNotThrowFilterExceptionWhenEndCreatedAtIsNull() {
            // Arrange
            LocalDate startCreatedAt = LocalDate.now().plusDays(3);

            // Act & Assert
            var linkFilter = new LinkFilter(false, startCreatedAt, null);
            assertDoesNotThrow(linkFilter::validate);
        }

        @Test
        void shouldNotThrowFilterExceptionWhenBothCreatedAtIsNull() {
            // Act & Assert
            var linkFilter = new LinkFilter(false, null, null);
            assertDoesNotThrow(linkFilter::validate);
        }

        @Test
        void shouldThrowFilterExceptionWhenStartCreatedAtIsAfterEndCreatedAt() {
            // Arrange
            LocalDate endCreatedAt = LocalDate.now().plusDays(3);
            LocalDate startCreatedAt = endCreatedAt.plusDays(7);

            // Act & Assert
            var linkFilter = new LinkFilter(false, startCreatedAt, endCreatedAt);
            assertThrows(FilterException.class, linkFilter::validate);
        }
    }
}