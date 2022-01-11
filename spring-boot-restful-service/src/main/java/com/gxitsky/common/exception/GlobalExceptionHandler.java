package com.gxitsky.common.exception;

import com.gxitsky.common.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @desc: 全局异常处理
 * @author: gxing
 * @date: 2019/1/30 10:29
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBean defaultErrorHandler(HttpServletRequest req, Exception e) {
        logger.error("", e);

        ResultBean resultBean = new ResultBean();
        resultBean.setMsg(e.getMessage());
        if (e instanceof NoHandlerFoundException) {
            resultBean.setCode(404);
        } else {
            resultBean.setCode(500);
        }
        resultBean.setState(false);
        resultBean.setData(null);
        return resultBean;
    }
}
