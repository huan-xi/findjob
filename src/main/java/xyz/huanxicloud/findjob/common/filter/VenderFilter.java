package xyz.huanxicloud.findjob.common.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import xyz.huanxicloud.findjob.common.Constant;
import xyz.huanxicloud.findjob.common.jwt.JwtVenderUtil;
import xyz.huanxicloud.findjob.pojo.Vender;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/vender/*",filterName = "venderFilter")
public class VenderFilter implements Filter {

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
            if (!JwtVenderUtil.isTokenExpired(token)){
                //验证用户值是否合法
                Vender vender= JwtVenderUtil.getVenderFormToken(token);
                if (vender!=null&&!StringUtils.isEmpty(vender.getVenderId())){
                    //验证用户是否被禁用
                    if (!vender.getStatus().equals(Constant.getRoueStatusForbid()))
                    filterChain.doFilter(httpRequest, servletResponse);
                    else httpResponse.getWriter().write("{\"status\":4005,\"msg\":\"vender forbid\"}");
                }else {
                    httpResponse.getWriter().write("{\"status\":4003,\"msg\":\"please login is vender error \"}");
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
