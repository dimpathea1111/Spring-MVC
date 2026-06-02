package co.istad.chhaya.itespringrestapi.exception;

import lombok.Builder;

@Builder
public record ErrorResponse<T>(
        boolean status,
        Integer code,
        String message,
        T errors
) {


}
