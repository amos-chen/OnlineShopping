package com.taotao.portal.interceptor;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ${chenlunwei} on 2017/8/4.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Value("${SSO_BASE_URL}")
    private String SSO_BASE_URL;
    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;

    //在handler处理之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        //从cookie中取token
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        //根据token获取用户信息
        TbUser tbUser = userService.getUserByToken(token);
        //如果没有渠道用户信息，则跳转登录页面
        if (tbUser == null) {
            response.sendRedirect(SSO_BASE_URL +SSO_LOGIN_URL+ "?redirect=" + request.getRequestURL());
            return false;
        }
        return true;
    }


    //返回modleAndView之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //返回modelAndView之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
