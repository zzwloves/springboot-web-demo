package com.example.demo.filter;

import com.example.demo.base.BaseConstant;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author zhuzw
 * @version <b>1.0.0</b>
 * @date 2019/8/12 11:27
 */
//@Order(1)
//@Component
//@WebFilter(filterName = "tokenFilter", urlPatterns = "/**", asyncSupported = true)
public class TokenFilter implements Filter {

    private static final String[] WHITE_LIST = {"/login", "/static"};

    @Autowired
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();
        if (Arrays.stream(WHITE_LIST).anyMatch(servletPath::startsWith)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        String token = request.getHeader("token");
        User user = userService.getByToken(token);
        request.setAttribute(BaseConstant.USER_INFO, user);
    }

    @Override
    public void destroy() {

    }
}
