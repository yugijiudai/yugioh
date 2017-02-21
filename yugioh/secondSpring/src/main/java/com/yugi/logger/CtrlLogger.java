package com.yugi.logger;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/7/5.
 */
@Aspect
@Component
public class CtrlLogger {

    private Logger ctrlLog = LogManager.getLogger(this.getClass());


    @Around("execution(* com.yugi.ctrl..*(..))")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable{
        ctrlLog.info("{}.{}()",point.getTarget().getClass().getName(),point.getSignature().getName());
        return point.proceed();
    }

}
