package com.servicevirtualization.user.controller;

import com.servicevirtualization.user.exception.ErrorInfo;
import com.servicevirtualization.user.exception.SVException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class UserExceptionHandler {
    @ResponseBody
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = SVException.class)
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, SVException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
//        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
}
