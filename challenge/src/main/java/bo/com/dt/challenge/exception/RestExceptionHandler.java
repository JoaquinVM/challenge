package bo.com.dt.challenge.exception;

import bo.com.dt.challenge.exception.dto.ApiErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> subErrors = new ArrayList<>();
        for (FieldError vFieldError: ex.getBindingResult().getFieldErrors()){
            subErrors.add(vFieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new ApiErrorDto("400", "Error de validation", "", subErrors), BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handle(BadRequestException ex) {
        return new ResponseEntity<>(new ApiErrorDto(ex.getCode(), ex.getMessage(), ex.getDetail()), BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handle(NotFoundException ex) {
        return new ResponseEntity<>(new ApiErrorDto(ex.getCode(), ex.getMessage(), ex.getDetail()), NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handle(RuntimeException ex) {
        log.error("Error Inesperado:", ex);
        return new ResponseEntity<>(new ApiErrorDto("500", "Unknown Error", ""), INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorDto("400", "Malformed JSON request", ""), HttpStatus.BAD_REQUEST);
    }
}
