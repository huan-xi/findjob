package xyz.huanxicloud.findjob.common.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import xyz.huanxicloud.findjob.common.jwt.JwtTokenUtil;
import xyz.huanxicloud.findjob.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/user/*",filterName = "userFilter")
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse=(HttpServletResponse) servletResponse;
        String token=((HttpServletRequest) servletRequest).getHeader("Token");
        if (!StringUtils.isEmpty(token)){
            //Token是否过期验证
            if (!JwtTokenUtil.isTokenExpired(token)){
                User user= JwtTokenUtil.getUserFormToken(token);
                if (user!=null&&StringUtils.isEmpty(user.getUserId())){
                    filterChain.doFilter(httpRequest, servletResponse);
                }else {
                    httpResponse.getWriter().write("{\"status\":4003,\"msg\":\"please login is user error \"}");
                }
            }else {
                httpResponse.getWriter().write("{\"status\":4003,\"msg\":\"please login is timeout\"}");
            }
        }else {
            httpResponse.getWriter().write("{\"status\":4003,\"msg\":\"please login\"}");
        }

    }

    @Override
    public void destroy() {

    }
}
