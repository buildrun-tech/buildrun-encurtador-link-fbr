package tech.buildrun.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.util.Map;

public class FilterException extends DomainException{

    private Map<String, Object> invalidFields;

    public FilterException(Map<String, Object> invalidFields) {
        this.invalidFields = invalidFields;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("There is a problem with the filters");
        pb.setDetail("Check the validations field.");

        pb.setProperties(Map.of("validations", invalidFields));

        return pb;
    }
}
