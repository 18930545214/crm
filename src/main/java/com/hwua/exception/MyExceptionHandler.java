
package com.hwua.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    public String handlerUnauthorizedException(){
        System.out.println("异常处理...");
        return "forward:/403";
    }
    @ExceptionHandler(Exception.class)
    public String handlerException(){
        System.out.println("异常处理...");
        return "forward:/403";
    }

}
