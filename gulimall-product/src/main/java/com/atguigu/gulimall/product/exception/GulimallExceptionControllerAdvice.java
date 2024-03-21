package com.atguigu.gulimall.product.exception;

import com.atguigu.common.exception.BizCodeEnume;
import com.atguigu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 集中处理异常
 * @Author LiTong(Prode)
 * @Data 2024/3/21 22:30
 */
@Slf4j
//@ControllerAdvice(basePackages = "com.atguigu.gulimall.product.controller")
@RestControllerAdvice(basePackages = "com.atguigu.gulimall.product.controller")
public class GulimallExceptionControllerAdvice {


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public R handleValidException(MethodArgumentNotValidException e) {

//      log.error("数据校验出现问题:{},异常类型:{}", e.getMessage(), e.getClass());
        Map<String, String> errorMap = new HashMap<>();
      e.getBindingResult().getFieldErrors().forEach(fieldError -> {
        log.error("错误字段:{}", fieldError.getField());
        log.error("错误信息:{}", fieldError.getDefaultMessage());
        errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
      });
     return R.error(BizCodeEnume.VAILD_EXCEPTION.getCode(),BizCodeEnume.VAILD_EXCEPTION.getMsg()).put("data", errorMap);

    }
    @ExceptionHandler(value = {Throwable.class})
    public R handlerException(Throwable e) {
        log.error("错误信息:{}", e.getMessage());
        return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(), BizCodeEnume.VAILD_EXCEPTION.getMsg());
    }
}
