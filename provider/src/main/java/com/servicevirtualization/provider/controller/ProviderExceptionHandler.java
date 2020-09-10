package com.servicevirtualization.provider.controller;

import com.servicevirtualization.provider.exception.ErrorInfo;
import com.servicevirtualization.provider.exception.SVException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ProviderExceptionHandler {
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
