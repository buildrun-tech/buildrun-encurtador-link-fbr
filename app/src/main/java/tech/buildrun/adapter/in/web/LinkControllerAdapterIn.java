package tech.buildrun.adapter.in.web;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.adapter.in.web.dto.ShortenLinkRequest;
import tech.buildrun.adapter.in.web.dto.ShortenLinkResponse;
import tech.buildrun.core.port.in.ShortenLinkPortIn;

import java.net.URI;
import java.util.UUID;

@RestController
@Validated
public class LinkControllerAdapterIn {

    private static final Logger logger = LoggerFactory.getLogger(LinkControllerAdapterIn.class);

    private final ShortenLinkPortIn shortenLinkPortIn;

    public LinkControllerAdapterIn(ShortenLinkPortIn shortenLinkPortIn) {
        this.shortenLinkPortIn = shortenLinkPortIn;
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

}
