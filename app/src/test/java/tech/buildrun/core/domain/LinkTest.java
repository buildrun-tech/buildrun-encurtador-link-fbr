package tech.buildrun.core.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkTest {

    @Nested
    class generateFullUrl {

        @Test
        void shouldGenerateFullUrlWhenThereIsNoUtmTags() {
            // Arrange
            String originalUrl = "https://google.com";
            UtmTags utmTags = new UtmTags(null, null, null, null);
            Link link = new Link(
                    UUID.randomUUID().toString(),
                    originalUrl,
                    utmTags,
                    null,
                    false,
                    null,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            // Act
            var fullUrl = link.generateFullUrl();

            // Assert
            assertEquals(originalUrl, fullUrl);
        }

        @Test
        void shouldGenerateFullUrlWhenThereIsUtmTags() {
            // Arrange
            String originalUrl = "https://google.com";
            UtmTags utmTags = new UtmTags("ig", "paid_social", "release", "post_livefbr");
            Link link = new Link(
                    UUID.randomUUID().toString(),
                    originalUrl,
                    utmTags,
                    null,
                    false,
                    null,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            // Act
            var fullUrl = link.generateFullUrl();

            // Assert
            var expectedFullUrl = String.format("%s?utm_source=ig&utm_medium=paid_social&utm_campaign=release&utm_content=post_livefbr",
                    originalUrl
            );

            assertEquals(expectedFullUrl, fullUrl);
        }
    }

}