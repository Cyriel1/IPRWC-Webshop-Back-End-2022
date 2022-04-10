package nl.hsleiden.webshop.aspect;

import nl.hsleiden.webshop.entity.payloads.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
