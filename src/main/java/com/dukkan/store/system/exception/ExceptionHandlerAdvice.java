package com.dukkan.store.system.exception;

import com.dukkan.store.system.ApiResponse;
import com.dukkan.store.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse handleValidationException(MethodArgumentNotValidException ex){
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());
        errors.forEach((error)->{
            String key = ((FieldError)error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });
        return new ApiResponse(
                false,
                StatusCode.INVALID_ARGUMENT,
                "Provided arguments are invalid, see data for details.",
                map
        );
    }

    @ExceptionHandler(UserEmailExistsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ApiResponse handleEmailExistsException(Exception ex){
        return new ApiResponse(
                false,
                StatusCode.UNAUTHORIZED,
                "email already exists",
                ex.getMessage()
        );
    }
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiResponse handleObjectNotFoundException(Exception ex){
        return new ApiResponse(
                false,
                StatusCode.NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMissingParameter(
            MissingServletRequestParameterException ex) {

        Map<String, String> errors = new HashMap<>();

        errors.put(
                ex.getParameterName(),
                "This parameter is required"
        );

        return new ApiResponse(
                false,
                StatusCode.INVALID_ARGUMENT,
                "Missing required parameter",
                errors
        );
    }

}
