package interfaz;

import Administrador.Administrador;
import Administrativo.Administrativo;
import Docente.Docente;
import Estudiante.Estudiante;
import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


@Named("login")
@SessionScoped
public class Login implements Serializable {
    private String Cedula;
    private String Password;
    private List<String> Roles;
    private String RolSeleccionado;
    private boolean UsuarioLogueado;
    
    @EJB
    private FacadeUsuario fUsr;
    
    public Login() {}
    
    public String getRolSeleccionado() {return RolSeleccionado;}
    
    public void setRolSeleccionado(String RolSeleccionado) {this.RolSeleccionado = RolSeleccionado;}
    
    public String getUsuario() {return Cedula;}
    
    public void setUsuario(String Usuario) {this.Cedula = Usuario;}
    
    public String getPassword() {return Password;}
    
    public void setPassword(String Password) {this.Password = Password;}
    
    public List<String> getRoles() {return Roles;}
    
    public void setRoles(List<String> Roles) {this.Roles = Roles;}
    
    public boolean isUsuarioLogueado() {return UsuarioLogueado;}
    
    public void setUsuarioLogueado(boolean UsuarioLogueado) {this.UsuarioLogueado = UsuarioLogueado;}
    
    public String login(){
        int idUsr= fUsr.ValidarLogin(Integer.valueOf(Cedula), Password, RolSeleccionado);
        if (idUsr!= -1) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            Usuario Usr = fUsr.BuscarUsuario(idUsr);
            request.getSession().setAttribute("Usuario", Usr);
            this.UsuarioLogueado = true;
            return "logueado";
        }
        return "nologueado";
    }
    
    public String logout(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.getSession().invalidate();
        this.UsuarioLogueado = false;
        return "nologueado";
    }
    
    @PostConstruct
    public void Init(){
        this.Roles = new ArrayList<>();
        Roles.add("Administrador");
        Roles.add("Administrativo");
        Roles.add("Docente");
        Roles.add("Estudiante");
        RolSeleccionado = Roles.get(0);
        
        fUsr.RegistrarUsuario("Administrador", "Admin@strador.edu.uy", "1234", 12345672, "Administrador", "", "");
        fUsr.RegistrarUsuario("Administrativo", "Admin@istrativo.edu.uy", "1234", 3456789, "Administrativo", "", "");
    }
    
}
