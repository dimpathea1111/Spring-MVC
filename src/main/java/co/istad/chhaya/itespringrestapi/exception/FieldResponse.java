package co.istad.chhaya.itespringrestapi.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldResponse {
    private String field;
    private String message;
}
