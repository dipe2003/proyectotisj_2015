package interfaz;

import Usuario.FacadeUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;


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
        if (fUsr.ExisteUsuario(Usuario, Password, RolSeleccionado)!= -1) {
            System.out.println("Existe!!!!!!!!!!");
        }        
        return "logueado";
    }
   
    @PostConstruct
    public void Init(){
        this.Roles = new ArrayList<>();
        Roles.add("Administrador");
        Roles.add("Administrativo");
        Roles.add("Docente");
        Roles.add("Estudiante");
        
        //fUsr.RegistrarUsuario("Admin", "Administrador", "Admin@strador.edu.uy", "1234", 1234567, "Administrador", "");
    }
   
}