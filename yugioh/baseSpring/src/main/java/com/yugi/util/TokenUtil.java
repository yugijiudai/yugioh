package com.yugi.util;

import com.yugi.constants.SpringConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

/**
 * @author yugi
 * @apiNote
 * @since 2017-02-09
 */
public final class TokenUtil {
    private TokenUtil() {
        //no instance
    }

    /**
     * 生成Token
     *
     * @param request
     */
    public static void buildToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String tokenValue = UUID.randomUUID().toString();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        //key是由当前的sessionId+url+时间戳组成,val是uuid
        String key = session.getId() + request.getRequestURI() + time;
        Object attribute = session.getAttribute(key);
        if (attribute == null) {
            session.setAttribute(key, tokenValue);
            //下面这两个是为了让页面能用el取出来,然后拼成session的key
            request.setAttribute(SpringConstant.TOKENVALUE, tokenValue);
            request.setAttribute(SpringConstant.TOKENTIME, time);
        }
    }

    /**
     * 往页面写入token并且加密
     *
     * @param request
     * @return
     */
    public static String getTokenValue(HttpServletRequest request) {
        Object url = request.getAttribute("javax.servlet.forward.request_uri");
        String sessionId = request.getSession().getId();
        Object tokenTime = request.getAttribute(SpringConstant.TOKENTIME);
        Object tokenValue = request.getAttribute(SpringConstant.TOKENVALUE);
        StringBuilder token = new StringBuilder();
        token.append(sessionId).append(",").append(url).append(tokenTime).append(",").append(tokenValue);
        return Base64.encodeBase64URLSafeString(token.toString().getBytes());
    }

    /**
     * 校验token,是否重复提交
     *
     * @param request
     * @return true表示重复提交
     */
    public static boolean checkRepeat(HttpServletRequest request) {
        String clientToken = request.getParameter(SpringConstant.TOKEN);
        if (clientToken == null) {
            return true;
        }
        clientToken = new String(Base64.decodeBase64(clientToken));
        //客户端token的值是由sessionId,+url+时间+,+tokenValue
        String[] split = clientToken.split(",");
        //因为tokenValue和time只能用一次,如果这个数组长度不是3证明是有可能是第二次重复提交
        if (split.length != 3) {
            return true;
        }
        HttpSession session = request.getSession();
        //这里的sessionId直接从服务器获取,不使用客户端存过来的
        String key = session.getId() + split[1];
        String val = split[2];
        Object serverToken = session.getAttribute(key);
        if (serverToken == null) {
            return true;
        }
        if (!serverToken.toString().equals(val)) {
            return true;
        }
        //return true表示重复,执行到这里表示这是第一次提交,所以要删除session
        session.removeAttribute(key);
        return false;
    }

}
