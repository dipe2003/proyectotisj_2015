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
   private String Usuario;
   private String Password;
   private List<String> Roles;
   private String RolSeleccionado;
   
   @EJB
   private FacadeUsuario fUsr;

    public Login() {
    }

    public String getRolSeleccionado() {
        return RolSeleccionado;
    }

    public void setRolSeleccionado(String RolSeleccionado) {
        this.RolSeleccionado = RolSeleccionado;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public List<String> getRoles() {
        return Roles;
    }

    public void setRoles(List<String> Roles) {
        this.Roles = Roles;
    }
    
    public String login(){
        int idUsr= fUsr.ExisteUsuario(Usuario, Password, RolSeleccionado);        
        if (idUsr!= -1) {            
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            Usuario Usr = fUsr.BuscarUsuario(idUsr);
            switch(RolSeleccionado){
                case "Administrador":
                    request.getSession().setAttribute("Administrador", (Administrador) Usr);
                    return "homeAdministrador";
                    
                case "Administrativo":
                    request.getSession().setAttribute("Administrativo", (Administrativo) Usr);
                    return "homeAdministrativo";
                    
                case "Docente":
                    request.getSession().setAttribute("Docente", (Docente) Usr);
                    return "homeDocente";
                    
                case "Estudiante":
                    request.getSession().setAttribute("Estudiante", (Estudiante) Usr);
                    return "homeEstudiante";
            }
        }        
        return "nologueado";
    }
   
    @PostConstruct
    public void Init(){
        this.Roles = new ArrayList<>();
        Roles.add("Administrador");
        Roles.add("Administrativo");
        Roles.add("Docente");
        Roles.add("Estudiante");
        
        fUsr.RegistrarUsuario("Admin", "Administrador", "Admin@strador.edu.uy", "1234", 1234567, "Administrador", "");
    }
   
}