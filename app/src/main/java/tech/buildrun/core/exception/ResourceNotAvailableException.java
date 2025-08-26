package tech.buildrun.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ResourceNotAvailableException extends DomainException{

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("Resource not available.");

        return pb;
    }
}
