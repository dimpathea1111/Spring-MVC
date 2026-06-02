
package co.istad.chhaya.itespringrestapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

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



//    2
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleValidationException(
//            MethodArgumentNotValidException e) {
//
//        log.info("Validation exception happened");
//
//        List<FieldResponse> errors = new ArrayList<>();
//
//        e.getBindingResult().getFieldErrors().forEach(error -> {
//            errors.add(
//                    new FieldResponse(
//                            error.getField(),
//                            error.getDefaultMessage()
//                    )
//            );
//        });
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("status", false);
//        response.put("code", 400);
//        response.put("message", "Validation is errored");
//        response.put("errors", errors);
//
//        return ResponseEntity.badRequest().body(response);
//    }
//
//

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
////    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e){
//    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e){
//        log.error("Validation Exception happed");
//
//        List<FieldResponse> fields=new ArrayList<>();
//        e.getFieldErrors()
//                .forEach(fieldError -> {
//                    FieldErrorResponse field=FieldErrorResponse.builder()
//                            .field(fieldError.getField())
//                            .message(fieldError.getDefaultMessage())
//                            .build();
//                    fields.add(field);
//
////                            .build();
////                    fields.add(field);
//                });
//
//
//        return ErrorResponse.builder()
//                .status(false)
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message("Validation is error")
//                .errors(fields)
//                .build()
//
//    }

@ExceptionHandler(ResponseStatusException.class)
public ResponseEntity<?>  handleServiceException(ResponseStatusException e){
    ErrorResponse<?> errorResponse=ErrorResponse.builder()
            .status(false)
            .code(e.getStatusCode().value())
            .message("Service exception errored")
            .errors(e.getReason())
//            .message(e.getMessage())
            .build();
    return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
}


@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {

    log.error("Validation Exception happened");

    List<FieldErrorResponse> fields = new ArrayList<>();

    e.getBindingResult().getFieldErrors().forEach(fieldError -> {
        FieldErrorResponse field = FieldErrorResponse.builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .build();
        fields.add(field);
    });

    return ResponseEntity.badRequest().body(
            ErrorResponse.builder()
                    .status(false)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Validation is error")
                    .errors(fields)
                    .build()
    );
}





}

