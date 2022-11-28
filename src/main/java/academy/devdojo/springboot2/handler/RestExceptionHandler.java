package academy.devdojo.springboot2.handler;

import academy.devdojo.springboot2.exception.BadRequestDetails;
import academy.devdojo.springboot2.exception.BadRequestionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestionException.class)
    public ResponseEntity<BadRequestDetails> handlerBadRequestionException(BadRequestionException badRequestionException){
        return new ResponseEntity<>(
                BadRequestDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check Documentation")
                        .details(badRequestionException.getMessage())
                        .developMessage(badRequestionException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
}
