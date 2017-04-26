package com.lifetea.config;

import com.lifetea.exception.UserException;
import com.lifetea.model.BaseResult;
import org.springframework.http.HttpRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    public BaseResult<ModelMap> searchErrorHandler(Exception e){
        BaseResult result = new BaseResult();
        result.setMsg(e.getMessage());
        result.setCode(400);
        e.printStackTrace();
        return result;
    }
    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpRequest request, Exception e){
        /*
        * 返回视图：
        * 定义一个ModelAndView即可，
        * 然后return;
        * 定义视图文件(比如：error.html,error.ftl,error.jsp);
        *
        */
        // 打印异常信息
        e.printStackTrace();
    }
}