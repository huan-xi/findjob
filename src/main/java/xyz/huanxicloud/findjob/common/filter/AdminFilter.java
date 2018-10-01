package xyz.huanxicloud.findjob.common.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/admin/*",filterName = "adminFilter")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse=(HttpServletResponse) servletResponse;
        String token=((HttpServletRequest) servletRequest).getHeader("Token");
        if (!StringUtils.isEmpty(token)){
            //Token验证
            
            filterChain.doFilter(httpRequest, servletResponse);
        }else {
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.getWriter().write("{\"status\":4003,\"msg\":\"请先登入\"}");
        }

    }

    @Override
    public void destroy() {

    }
}
