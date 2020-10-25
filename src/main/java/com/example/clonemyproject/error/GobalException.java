package com.example.clonemyproject.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GobalException extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status",status.value());
        //get all error
        List<String> err = ex.getBindingResult().getAllErrors() //getAllErrors lấy tất cả lỗi,cả lỗi global, getFieldError chi lay trong field
                .stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());
        body.put("error",err);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity errorMessage(Exception ex,WebRequest request){
        String e= ex.getLocalizedMessage();
        ErrorDetails errorDetail = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(errorDetail,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
