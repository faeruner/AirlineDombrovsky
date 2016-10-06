package by.pvt.module3.filter;

import by.pvt.module3.service.common.ServiceUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller/*"})
public class ServletSecurityFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
        ServiceUtil.closeSession();
    }

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
