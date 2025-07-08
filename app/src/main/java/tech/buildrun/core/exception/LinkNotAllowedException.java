package tech.buildrun.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class LinkNotAllowedException extends DomainException{

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);

        pb.setTitle("The user does not have access for this link.");
        pb.setDetail("The user can only see the analytics of its own links.");

        return pb;
    }
}
