package tech.buildrun.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class LinkNotFoundException extends DomainException{

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Link Not Found Exception.");
        pb.setDetail("Link not found.");

        return pb;
    }
}
