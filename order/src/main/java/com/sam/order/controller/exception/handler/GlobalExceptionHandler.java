package com.sam.order.controller.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sam.order.controller.dto.ErrorResponseDto;
import com.sam.order.domain.exception.OrderAlreadyInitialized;
import com.sam.order.domain.exception.OrderItemPriceInvalid;
import com.sam.order.domain.exception.OrderNotFound;
import com.sam.order.domain.exception.OrderPriceInvalid;
import com.sam.order.domain.exception.OrderStatusInvalid;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus httpStatusOf(RuntimeException runtimeException) {
        if (runtimeException.getClass().isAnnotationPresent(ResponseStatus.class) ) {
            return runtimeException.getClass().getAnnotation(ResponseStatus.class).value();
        }

        return HttpStatus.NOT_IMPLEMENTED;
    }

    @ExceptionHandler({OrderAlreadyInitialized.class, OrderItemPriceInvalid.class, OrderNotFound.class, OrderPriceInvalid.class, OrderStatusInvalid.class})
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(RuntimeException exception,
                                                                                 WebRequest webRequest){
        HttpStatus httpStatus = httpStatusOf(exception);

        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                httpStatus,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, httpStatus);
    }

}
