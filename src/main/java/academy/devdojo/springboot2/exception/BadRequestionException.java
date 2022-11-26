package academy.devdojo.springboot2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestionException extends RuntimeException{

    public BadRequestionException(String message) {
        super(message);
    }
}
