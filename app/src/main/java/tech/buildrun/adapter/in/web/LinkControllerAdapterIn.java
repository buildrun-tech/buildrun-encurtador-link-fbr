package tech.buildrun.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.adapter.in.web.dto.*;
import tech.buildrun.core.domain.LinkFilter;
import tech.buildrun.core.port.in.LinkAnalyticsPortIn;
import tech.buildrun.core.port.in.RedirectPortIn;
import tech.buildrun.core.port.in.ShortenLinkPortIn;
import tech.buildrun.core.port.in.UserLinksPortIn;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@Validated
public class LinkControllerAdapterIn {

    private static final Logger logger = LoggerFactory.getLogger(LinkControllerAdapterIn.class);

    private final ShortenLinkPortIn shortenLinkPortIn;
    private final RedirectPortIn redirectPortIn;
    private final LinkAnalyticsPortIn linkAnalyticsPortIn;
    private final UserLinksPortIn userLinksPortIn;

    public LinkControllerAdapterIn(ShortenLinkPortIn shortenLinkPortIn,
                                   RedirectPortIn redirectPortIn,
                                   LinkAnalyticsPortIn linkAnalyticsPortIn,
                                   UserLinksPortIn userLinksPortIn) {
        this.shortenLinkPortIn = shortenLinkPortIn;
        this.redirectPortIn = redirectPortIn;
        this.linkAnalyticsPortIn = linkAnalyticsPortIn;
        this.userLinksPortIn = userLinksPortIn;
    }

    @PostMapping(value = "/links")
    public ResponseEntity<ShortenLinkResponse> shortenLink(@RequestBody @Valid ShortenLinkRequest req,
                                                            HttpServletRequest servletRequest,
                                                            JwtAuthenticationToken token) {

        logger.info("Request {} - {}", req, token);

        var userId = UUID.fromString(token.getToken().getSubject());

        var linkId = shortenLinkPortIn.execute(req.toDomain(userId));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("links", linkId);

        var uri = URI.create(redirectUrl);

        return ResponseEntity.created(uri).body(new ShortenLinkResponse(redirectUrl));
    }

    @GetMapping(value = "/{linkId}")
    public ResponseEntity<ShortenLinkResponse> redirect(@PathVariable(name = "linkId") String linkId) {

        var fullUrl = redirectPortIn.execute(linkId);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(URI.create(fullUrl));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @GetMapping(value = "/links")
    public ResponseEntity<ApiResponse<LinkResponse>> userLinks(@RequestParam(name = "nextToken", defaultValue = "") String nextToken,
                                                               @RequestParam(name = "limit", defaultValue = "3") Integer limit,
                                                               @RequestParam(name = "active", required = false) Boolean active,
                                                               @RequestParam(name = "startCreatedAt", required = false) LocalDate startCreatedAt,
                                                               @RequestParam(name = "endCreatedAt", required = false) LocalDate endCreatedAt,
                                                               JwtAuthenticationToken token) {

        var userId = String.valueOf(token.getTokenAttributes().get("sub"));

        var body = userLinksPortIn.execute(userId, nextToken, limit, new LinkFilter(active, startCreatedAt, endCreatedAt));

        return ResponseEntity.ok(
            new ApiResponse<>(
                    body.items().stream().map(LinkResponse::fromDomain).toList(),
                    body.nextToken()
            )
        );
    }

    @GetMapping(value = "/links/{linkId}/analytics")
    public ResponseEntity<AnalyticsResponse> linkAnalytics(@PathVariable("linkId") String linkId,
                                                           @RequestParam(name = "startDate") LocalDate startDate,
                                                           @RequestParam(name = "endDate") LocalDate endDate,
                                                           JwtAuthenticationToken token) {

        var userId = String.valueOf(token.getTokenAttributes().get("sub"));

        var body = linkAnalyticsPortIn.execute(userId, linkId, startDate, endDate);

        return ResponseEntity.ok(body);
    }

}
