package kaiser0.com.myspringboot.exception;

import kaiser0.com.myspringboot.dto.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Result<Object>> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(Result.error(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Object>> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(Result.error("Hata: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}