package tech.buildrun.adapter.in.web;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.adapter.in.web.dto.ApiResponse;
import tech.buildrun.adapter.in.web.dto.LinkResponse;
import tech.buildrun.adapter.in.web.dto.ShortenLinkRequest;
import tech.buildrun.adapter.in.web.dto.ShortenLinkResponse;
import tech.buildrun.core.port.in.MyLinksPortIn;
import tech.buildrun.core.port.in.RedirectPortIn;
import tech.buildrun.core.port.in.ShortenLinkPortIn;

import java.net.URI;
import java.util.UUID;

@RestController
@Validated
public class LinkControllerAdapterIn {

    private static final Logger logger = LoggerFactory.getLogger(LinkControllerAdapterIn.class);

    private final ShortenLinkPortIn shortenLinkPortIn;
    private final RedirectPortIn redirectPortIn;
    private final MyLinksPortIn myLinksPortIn;

    public LinkControllerAdapterIn(ShortenLinkPortIn shortenLinkPortIn,
                                   RedirectPortIn redirectPortIn,
                                   MyLinksPortIn myLinksPortIn) {
        this.shortenLinkPortIn = shortenLinkPortIn;
        this.redirectPortIn = redirectPortIn;
        this.myLinksPortIn = myLinksPortIn;
    }

    @PostMapping(value = "/links")
    public ResponseEntity<ShortenLinkResponse> shortenLink(@RequestBody @Valid ShortenLinkRequest req,
                                                           JwtAuthenticationToken token) {

        logger.info("Request {} - {}", req, token);

        var userId = UUID.fromString(token.getToken().getSubject());

        var res = shortenLinkPortIn.execute(req.toDomain(userId));

        var uri = URI.create(res.shortenUrl());

        return ResponseEntity.created(uri).body(res);
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
                                                               JwtAuthenticationToken token) {

        var userId = String.valueOf(token.getTokenAttributes().get("sub"));

        var body = myLinksPortIn.execute(userId, nextToken, limit);

        return ResponseEntity.ok(
            new ApiResponse<>(
                    body.items().stream().map(LinkResponse::fromDomain).toList(),
                    body.nextToken()
            )
        );
    }

}
