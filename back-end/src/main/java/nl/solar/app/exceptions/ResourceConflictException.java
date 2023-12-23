package nl.solar.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflictException extends Exception {
    public ResourceConflictException(String message) {
        super(message);
    }
}
