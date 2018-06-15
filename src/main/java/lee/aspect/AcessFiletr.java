package lee.aspect;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 描述:登录控制
 *
 * @author Leo
 * @create 2018-06-09 上午 12:15
 */
@Order(1)
//重点
@Component
@WebFilter(filterName = "tokenFilter", urlPatterns = "*")
public class AcessFiletr implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        // 除了登录页面以外的页面和登录action以外，检查登录情况，未登录的需要重定向并且不通过过滤
//        if (uri.indexOf("admin/login") == -1 && uri.indexOf("login.jsp") == -1 && !uri.equals("/")) {
//            if ( uri.indexOf("js") >0 || uri.indexOf("css")>=0 || uri.indexOf("images")>=0
//                    || uri.indexOf("fonts")>=0 || uri.indexOf("lib")>=0 || uri.indexOf("ico")>=0){//不过滤静态资源
//                filterChain.doFilter(request, response);
//                return;
//            }
//            if ((userName == null || userName.equals(""))) {
//                response.sendRedirect("/login.jsp");
//                return;
//            }
//        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
