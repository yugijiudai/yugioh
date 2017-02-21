package com.yugi.Interceptor;

import com.yugi.annotation.CheckToken;
import com.yugi.annotation.Token;
import com.yugi.constants.SpringConstant;
import com.yugi.util.TokenUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yugi
 * @apiNote
 * @since 2017-02-09
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null) {
                TokenUtil.buildToken(request);
                return true;
            }
            CheckToken checkToken = method.getAnnotation(CheckToken.class);
            if (checkToken != null) {
                if (TokenUtil.checkRepeat(request)) {
                    request.setAttribute(SpringConstant.REPEATEDMSG, "repeated");
                    return super.preHandle(request, response, handler);
                    // return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }


}
