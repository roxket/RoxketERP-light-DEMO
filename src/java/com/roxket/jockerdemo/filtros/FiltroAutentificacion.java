 
package com.roxket.jockerdemo.filtros;

import com.roxket.jockerdemo.excepciones.NoLogueadoException;
import com.roxket.jockerdemo.modelos.Usuario;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oxker Studio
 */
@WebFilter(filterName = "FiltroAutentificacion", urlPatterns = {"/*"})
public class FiltroAutentificacion implements Filter {
	
	private static final boolean debug = true;

	private FilterConfig filterConfig = null;
    
    public FiltroAutentificacion() {
    }    
    
    private boolean autorizarURL(String url){
        System.out.println("url solicitante: " + url);
        return url.contains("index.jsp") || 
               url.contains("registrarse.jsp") || 
              // url.equals("/Roxket/") ||
               url.contains("/bootstrap/") || 
               url.contains("/css/") ||
               url.contains("/js/")||
			   url.contains("/vendor/")||
			   url.contains("/img/")||
			   url.contains("/scss/")||
               url.startsWith("usuarios", "/Roxket/".length());
    }
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException, Throwable {
        HttpServletRequest peticion = (HttpServletRequest)request;
        HttpServletResponse respuesta = (HttpServletResponse)response;

        boolean urlValida = autorizarURL(peticion.getRequestURI());
        
        Usuario usuario = (Usuario)peticion.getSession().getAttribute("user");
        if (usuario == null) {
            if (!urlValida){
                throw new ServletException(new NoLogueadoException("Es necesario loguearse antes de entrar al sistema"));
            } 
        }
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        Throwable problem = null;
        HttpServletRequest peticion = (HttpServletRequest)request;
        HttpServletResponse respuesta = (HttpServletResponse)response;
        try {
            doBeforeProcessing(request, response);
            chain.doFilter(request, response);
        } catch (Throwable t) {
            t.printStackTrace();
            problem = t;
            if (t instanceof NoLogueadoException) {
                request.getRequestDispatcher("index.jsp").forward(peticion, respuesta);
            }
        }
        
        doAfterProcessing(request, response);

        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            //sendProcessingError(problem, response);
        }
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("FiltroAutentificacion:Initializing filter");
            }
        }
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroAutentificacion()");
        }
        StringBuffer sb = new StringBuffer("FiltroAutentificacion(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
//    private void sendProcessingError(Throwable t, ServletResponse response) {
//        String stackTrace = getStackTrace(t);        
//        
//        if (stackTrace != null && !stackTrace.equals("")) {
//            try {
//                response.setContentType("text/html");
//                PrintStream ps = new PrintStream(response.getOutputStream());
//                PrintWriter pw = new PrintWriter(ps);                
//                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
//
//                // PENDING! Localize this for next official release
//                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
//                pw.print(stackTrace);                
//                pw.print("</pre></body>\n</html>"); //NOI18N
//                pw.close();
//                ps.close();
//                response.getOutputStream().close();
//            } catch (Exception ex) {
//            }
//        } else {
//            try {
//                PrintStream ps = new PrintStream(response.getOutputStream());
//                t.printStackTrace(ps);
//                ps.close();
//                response.getOutputStream().close();
//            } catch (Exception ex) {
//            }
//        }
//    }
    
//    public static String getStackTrace(Throwable t) {
//        String stackTrace = null;
//        try {
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            t.printStackTrace(pw);
//            pw.close();
//            sw.close();
//            stackTrace = sw.getBuffer().toString();
//        } catch (Exception ex) {
//        }
//        return stackTrace;
//    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
	
}
