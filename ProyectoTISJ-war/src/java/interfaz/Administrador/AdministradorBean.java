
package interfaz.Administrador;

import Administrador.Administrador;
import Administrador.FacadeAdministrador;
import Utilidades.Cedula;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("administradorBean")
@SessionScoped
public class AdministradorBean implements Serializable{
    
    private String CedulaAdministrador;
    private String NombreAdministrador; 
    private String ImagenAdministrador;
    private String CorreoAdministrador;
    
   @EJB
   private FacadeAdministrador fAdmin;
   
   @EJB
   private Cedula CI;
    
    public AdministradorBean() {}

    public String getNombreAdministrador() {return NombreAdministrador;}

    public void setNombreAdministrador(String NombreAdministrador) {this.NombreAdministrador = NombreAdministrador;}

    public String getImagenAdministrador() {return ImagenAdministrador;}

    public void setImagenAdministrador(String ImagenAdministrador) {this.ImagenAdministrador = ImagenAdministrador;}

    public String getCorreoAdministrador() {return CorreoAdministrador;}

    public void setCorreoAdministrador(String CorreoAdministrador) {this.CorreoAdministrador = CorreoAdministrador;}

    public String getCedulaAdministrador() {return CedulaAdministrador;}

    public void setCedulaAdministrador(String CedulaAdministrador) {this.CedulaAdministrador = CedulaAdministrador;}
    
    @PostConstruct
    public void Init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Administrador  Admin = (Administrador)request.getSession().getAttribute("Administrador");
        this.NombreAdministrador = Admin.getNombreUsuario();
        this.ImagenAdministrador = Admin.getImagenURL();
        this.CorreoAdministrador = Admin.getCorreoUsuario();
        this.CedulaAdministrador = String.valueOf(Admin.getCedulaUsuario());
    }
    
    public void modificarAdministrador(String actualPass, String newPass, String confrimPass){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Administrador  Admin = (Administrador)request.getSession().getAttribute("Administrador");
        if ((CI.EsCedulaValida(this.CedulaAdministrador)) && (newPass.equals(confrimPass)) && (Admin.isValidPass(actualPass))){
            Admin.setCedulaUsuario(Integer.valueOf(this.CedulaAdministrador));
            Admin.setNombreUsuario(this.NombreAdministrador);
            Admin.setPasswordUsuario(newPass);
            Admin.setCorreoUsuario(this.CorreoAdministrador);
            Admin.setImagenURL(this.ImagenAdministrador);
            fAdmin.ModificarAdministrador(Admin);
        }
        
    }  
}
