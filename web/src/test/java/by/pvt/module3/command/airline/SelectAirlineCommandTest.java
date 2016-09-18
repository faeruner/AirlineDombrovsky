package by.pvt.module3.command.airline;

import junit.framework.Assert;
import junit.framework.TestCase;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

public class SelectAirlineCommandTest extends TestCase {
    private HttpServletRequest request;
//    private Integer id;

    public void setUp() throws Exception {
        request = new HttpServletRequest ()
        {
            public String getAuthType() {
                return null;
            }

            public Cookie[] getCookies() {
                return new Cookie[0];
            }

            public long getDateHeader(String s) {
                return 0;
            }

            public String getHeader(String s) {
                return null;
            }

            public Enumeration<String> getHeaders(String s) {
                return null;
            }

            public Enumeration<String> getHeaderNames() {
                return null;
            }

            public int getIntHeader(String s) {
                return 0;
            }

            public String getMethod() {
                return null;
            }

            public String getPathInfo() {
                return null;
            }

            public String getPathTranslated() {
                return null;
            }

            public String getContextPath() {
                return null;
            }

            public String getQueryString() {
                return null;
            }

            public String getRemoteUser() {
                return null;
            }

            public boolean isUserInRole(String s) {
                return false;
            }

            public Principal getUserPrincipal() {
                return null;
            }

            public String getRequestedSessionId() {
                return null;
            }

            public String getRequestURI() {
                return null;
            }

            public StringBuffer getRequestURL() {
                return null;
            }

            public String getServletPath() {
                return null;
            }

            public HttpSession getSession(boolean b) {
                return null;
            }

            public HttpSession getSession() {
                return null;
            }

            public String changeSessionId() {
                return null;
            }

            public boolean isRequestedSessionIdValid() {
                return false;
            }

            public boolean isRequestedSessionIdFromCookie() {
                return false;
            }

            public boolean isRequestedSessionIdFromURL() {
                return false;
            }

            public boolean isRequestedSessionIdFromUrl() {
                return false;
            }

            public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
                return false;
            }

            public void login(String s, String s1) throws ServletException {

            }

            public void logout() throws ServletException {

            }

            public Collection<Part> getParts() throws IOException, ServletException {
                return null;
            }

            public Part getPart(String s) throws IOException, ServletException {
                return null;
            }

            public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
                return null;
            }

            private final Map<String, String[]> params = new HashMap<String, String[]>();

            public Map<String, String[]> getParameterMap()
            {
                return params;
            }

            public String getProtocol() {
                return null;
            }

            public String getScheme() {
                return null;
            }

            public String getServerName() {
                return null;
            }

            public int getServerPort() {
                return 0;
            }

            public BufferedReader getReader() throws IOException {
                return null;
            }

            public String getRemoteAddr() {
                return null;
            }

            public String getRemoteHost() {
                return null;
            }

            public void setAttribute(String s, Object o) {

            }

            public void removeAttribute(String s) {

            }

            public Locale getLocale() {
                return null;
            }

            public Enumeration<Locale> getLocales() {
                return null;
            }

            public boolean isSecure() {
                return false;
            }

            public RequestDispatcher getRequestDispatcher(String s) {
                return null;
            }

            public String getRealPath(String s) {
                return null;
            }

            public int getRemotePort() {
                return 0;
            }

            public String getLocalName() {
                return null;
            }

            public String getLocalAddr() {
                return null;
            }

            public int getLocalPort() {
                return 0;
            }

            public ServletContext getServletContext() {
                return null;
            }

            public AsyncContext startAsync() throws IllegalStateException {
                return null;
            }

            public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
                return null;
            }

            public boolean isAsyncStarted() {
                return false;
            }

            public boolean isAsyncSupported() {
                return false;
            }

            public AsyncContext getAsyncContext() {
                return null;
            }

            public DispatcherType getDispatcherType() {
                return null;
            }

            public Object getAttribute(String s) {
                return null;
            }

            public Enumeration<String> getAttributeNames() {
                return null;
            }

            public String getCharacterEncoding() {
                return null;
            }

            public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

            }

            public int getContentLength() {
                return 0;
            }

            public long getContentLengthLong() {
                return 0;
            }

            public String getContentType() {
                return null;
            }

            public ServletInputStream getInputStream() throws IOException {
                return null;
            }

            public String getParameter(String name)
            {
                String[] matches = params.get(name);
                if (matches == null || matches.length == 0) return null;
                return matches[0];
            }

            public Enumeration<String> getParameterNames() {
                return null;
            }

            public String[] getParameterValues(String s) {
                return new String[0];
            }

            // TODO *many* methods to implement here
        };
//        CommonDAO<Airline> daoAirline = new CommonDAO<Airline>(Airline.class);
//        id = daoAirline.add(new Airline());
//        String[] tmp = {id.toString()};
//        request.getParameterMap().put(Airline.ID, tmp);
    }

    public void tearDown() throws Exception {
//        CommonDAO<Airline> daoAirline = new CommonDAO<Airline>(Airline.class);
//        if(id != null)
//            daoAirline.delete(id);
    }

    public void testExecute(){
        String pageExist = (new AirlineCommand()).execute(request);
        String pageWaiting = "/jsp/airlines.jsp";
        Assert.assertEquals(pageExist, pageWaiting);
    }


}