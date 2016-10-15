package com.yugi.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by Administrator on 2016/5/20.
 */
@Aspect
public class ExceptionLogger {

    private Logger errorLog = LogManager.getLogger(LogType.ERROR);

    @Around("within(com.yugi..*)")
    public Object loggerException(ProceedingJoinPoint p) throws Throwable {
        Object obj;
        try {
            // 调用目标组件
            obj = p.proceed();
        }
        catch (Throwable e) {
            // 记录日志
            String className = p.getTarget().getClass().getName();
            String method = p.getSignature().getName();
            StackTraceElement[] stackTrace = e.getStackTrace();
            StringBuilder sb = new StringBuilder();
            // 循环记录每一行异常信息
            for (StackTraceElement elem : stackTrace)
                sb.append("\t").append(elem.toString()).append("\n");
            errorLog.error("[" + className + "." + method + "]error:" + "\n" + sb.toString());
            throw e;
        }
        // 不要丢掉目标组件的返回值
        return obj;
    }
}

