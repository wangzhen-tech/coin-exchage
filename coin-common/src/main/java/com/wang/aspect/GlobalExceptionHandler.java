package com.wang.aspect;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.wang.model.R;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @Author wangzhen
 * @Description 全局异常处理
 * @Date 2025/4/3 10:20
 * @Version 1.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 1 内部API调用的异常处理
     */
    @ExceptionHandler(value = ApiException.class)
    public R handlerApiException(ApiException exception){
        IErrorCode errorCode = exception.getErrorCode();
        // 有错误码就返回错误码，没有返回错误信息
        if(errorCode!=null){
            return R.fail(errorCode) ;
        }
        return R.fail(exception.getMessage()) ;
    }

    /**
     * 2 方法参数校验失败的异常
     * MethodArgumentNotValidException
     *
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        if(bindingResult.hasErrors()){// 获取参数校验失败的字段，并返回错误信息
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError!=null){
                return R.fail(fieldError.getField()+fieldError.getDefaultMessage()) ;
            }
        }
        return R.fail(exception.getMessage()) ;
    }

    /**
     * 3 对象内部使用Validate 没有校验成功的异常
     */
    @ExceptionHandler(BindException.class)
    public R handlerBindException(BindException bindException){
        BindingResult bindingResult = bindException.getBindingResult();
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError!=null){
                return R.fail(fieldError.getField()+fieldError.getDefaultMessage()) ;
            }
        }
        return R.fail(bindException.getMessage()) ;
    }
}
