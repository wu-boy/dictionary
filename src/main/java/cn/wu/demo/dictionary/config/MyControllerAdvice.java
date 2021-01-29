package cn.wu.demo.dictionary.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 控制器通知
 * @author wusq
 * @date 2020/9/30
 */
@ControllerAdvice
public class MyControllerAdvice {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 参数校验异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    public HttpResult exceptionHandle(MethodArgumentNotValidException exception){
        HttpResult result = new HttpResult();
        result.setCode(HttpStatus.BAD_REQUEST.value());
        List<ObjectError> list = exception.getBindingResult().getAllErrors();
        if(list != null){
            // 将参数校验提示信息拼接起来
            String msg = list.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("；"));
            result.setMessage(msg);
        }
        log.warn("参数校验错误：{}", result.getMessage());
        return result;
    }

    /**
     * 兜底异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResult exceptionHandle(Exception exception){
        HttpResult result = new HttpResult();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage(exception.getMessage());
        log.error("服务器错误：", exception);
        return result;
    }

}
