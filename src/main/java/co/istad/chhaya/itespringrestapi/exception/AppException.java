
package co.istad.chhaya.itespringrestapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class AppException {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleValidationException(
//            MethodArgumentNotValidException ex
//    ) {
//
//        log.error("Validation Exception handler");
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Validation failed");
//
//        return ResponseEntity
//                .badRequest()
//                .body(response);
//    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(
            MethodArgumentNotValidException e) {

        log.info("Validation exception happened");

        List<FieldResponse> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(
                    new FieldResponse(
                            error.getField(),
                            error.getDefaultMessage()
                    )
            );
        });

        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("code", 400);
        response.put("message", "Validation is errored");
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }

}

