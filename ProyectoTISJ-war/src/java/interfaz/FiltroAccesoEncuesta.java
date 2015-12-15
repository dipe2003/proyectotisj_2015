package interfaz;

import Usuario.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/Encuesta/RegistrarEncuesta.xhtml",
                            "/Encuesta/RegistrarPregunta.xhtml",
                            "/Encuesta/ImportarEncuesta.xhtml*"
                        })
public class FiltroAccesoEncuesta implements Filter{
    
    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }
    
    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sr;
        HttpServletResponse resp = (HttpServletResponse) sr1;
        Usuario session = (Usuario) req.getSession().getAttribute("Usuario");
        
        if (session != null && session.getClass().getSimpleName().equals("Administrador") ) {
            fc.doFilter(req, resp);
        } else {
            HttpServletResponse res = (HttpServletResponse) resp;
            res.sendRedirect(req.getContextPath() + "/Error/Error401.xhtml");
        }
    }
    
    @Override
    public void destroy() {
        
    }
    
}

