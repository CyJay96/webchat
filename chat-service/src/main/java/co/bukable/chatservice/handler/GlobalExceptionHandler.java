package co.bukable.chatservice.handler;

import co.bukable.chatservice.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", HttpStatus.BAD_REQUEST.toString());
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorResponse.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errorResponse, headers, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDataNotFoundException(RuntimeException exception) {
        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", HttpStatus.NOT_FOUND.toString());
        errorResponse.put("message", exception.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception exception) {
        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorResponse.put("message", exception.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
