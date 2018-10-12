package xyz.huanxicloud.findjob.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.huanxicloud.findjob.common.ReturnMessage;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ReturnMessage allExceptionHandler(HttpServletRequest request,
                                             Exception exception) throws Exception {
        exception.printStackTrace();
        return new ReturnMessage(5000,"服务器异常，请联系管理员！");
    }

}
