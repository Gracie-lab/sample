package com.medicine.medicineProject.exceptions;

import com.medicine.medicineProject.response.ApiResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField().toUpperCase() + "field: " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return getExceptionResponseEntity(validationErrors);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation (ConstraintViolationException exception){
            List<String> validationErrors = exception.getConstraintViolations().stream()
                    .map(violation -> violation.getPropertyPath() + "field: " + violation.getMessage())
                    .collect(Collectors.toList());
            return getExceptionResponseEntity(validationErrors);
    }


    private ResponseEntity<Object> getExceptionResponseEntity(List<String> errors){
//        final String errorMessage = String.valueOf(Messages.getMessage().values());
        final String errorsMessage = CollectionUtils.isNotEmpty(errors) ? errors.stream()
                .filter(StringUtils::isNotEmpty).collect(Collectors.joining(", ")): HttpStatus.BAD_REQUEST.getReasonPhrase();

        return new ResponseEntity<>(new ApiResponse(false, errorsMessage), HttpStatus.BAD_REQUEST);

    }
}
