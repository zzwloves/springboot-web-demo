package com.example.demo.handler;

import com.example.demo.base.BaseConstant;
import com.example.demo.base.ResponseResult;
import com.example.demo.base.ExceptionEnum;
import com.example.demo.base.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 10:24
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseResult handleBusinessException(BusinessException e) {
        log.info("发生系统定义的业务异常！", e);
        ResponseResult result = new ResponseResult();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());
        return result;
    }

    /**
     * 参数绑定异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ResponseResult handleBindException(BindException e) {
        log.error("参数绑定校验异常", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    @ResponseBody
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class})
    public ResponseResult handleServletException(Exception e) {
        log.info("发生servlet级别异常！", e);
        ResponseResult result = new ResponseResult();
        result.setCode(ExceptionEnum.SYSTEM_ERROR.getCode());
        if (BaseConstant.ENV_PROD.equals(profile)) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如404.
            result.setMsg(ExceptionEnum.SYSTEM_ERROR.getMsg());
        } else {
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleValidException(MethodArgumentNotValidException e) {
        log.error("参数绑定校验异常", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private ResponseResult wrapperBindingResult(BindingResult bindingResult) {
        String errorMsg = bindingResult.getAllErrors()
                .parallelStream()
                .map(ObjectError::getDefaultMessage)
                .reduce((e1, e2) -> e1 + "," + e2)
                .orElse("参数错误");
        ResponseResult result = new ResponseResult();
        result.setCode(4000);
        result.setMsg(errorMsg);
        return result;
    }


    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResponseResult handleUnknownException(Throwable t) {
        log.info("发生系统未知异常！", t);
        ResponseResult result = new ResponseResult();
        result.setCode(ExceptionEnum.SYSTEM_ERROR.getCode());
        result.setMsg(ExceptionEnum.SYSTEM_ERROR.getMsg());
        return result;
    }
}
