package by.pvt.module3.filter;

import by.pvt.module3.service.common.ServiceUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/controller" }, servletNames = { "WorkController" })
public class ServletSecurityFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		UserType type = (UserType) session.getAttribute("userType");
		if (type == null || UserType.GUEST.equals(type)) {
            ServiceUtil.closeSession();
            type = UserType.GUEST;
			session.setAttribute("userType", type);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		// pass the request along the by.pvt.module3.filter chain
		chain.doFilter(request, response);
        ServiceUtil.closeSession();
    }

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
