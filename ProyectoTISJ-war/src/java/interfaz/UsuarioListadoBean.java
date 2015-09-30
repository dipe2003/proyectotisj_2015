
package interfaz;

import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class UsuarioListadoBean implements Serializable{
    private String Rol;
    
    @EJB
    private FacadeUsuario fUsr;
    
    private List<Usuario> Usuarios;
    private Usuario UsuarioSeleccionado;
    
    //  Getters
    public List<Usuario> getUsuarios() {return this.Usuarios;}
    public Usuario getUsuarioSeleccionado() {return this.UsuarioSeleccionado;}
    public String getRol() {return Rol;}
    
    //  Setters
    public void setRol(String Rol){this.Rol = Rol;}
    public void setUsuarios(List<Usuario> Usuarios) {this.Usuarios = Usuarios;}
    public void setUsuarioSeleccionado(Usuario UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}
    
    @PostConstruct
    public void Init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Rol = request.getParameter("rol");
        this.Usuarios = fUsr.listarUsuarios(Rol);
    }
    
    
}
