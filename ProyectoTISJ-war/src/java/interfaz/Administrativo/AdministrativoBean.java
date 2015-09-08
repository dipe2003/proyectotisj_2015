
package interfaz.Administrativo;

import Administrativo.Administrativo;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@SessionScoped
public class AdministrativoBean implements Serializable{
    
    private String NombreAdministrativo; 
    private String ImagenAdministrativo;
    private String CorreoAdministrativo;
    private String CedulaAdministrativo;
    
    public AdministrativoBean() {}

    public String getNombreAdministrativo() {return NombreAdministrativo;}

    public void setNombreAdministrativo(String NombreAdministrativo) {this.NombreAdministrativo = NombreAdministrativo;}

    public String getImagenAdministrativo() {return ImagenAdministrativo;}

    public void setImagenAdministrativo(String ImagenAdministrativo) {this.ImagenAdministrativo = ImagenAdministrativo;}

    public String getCorreoAdministrativo() {return CorreoAdministrativo;}

    public void setCorreoAdministrativo(String CorreoAdministrativo) {this.CorreoAdministrativo = CorreoAdministrativo;}

    public String getCedulaAdministrativo() {return CedulaAdministrativo;}

    public void setCedulaAdministrativo(String CedulaAdministrativo) {this.CedulaAdministrativo = CedulaAdministrativo;}
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Administrativo  Admin = (Administrativo)request.getSession().getAttribute("Administrativo");
        this.NombreAdministrativo = Admin.getNombreUsuario();
        this.ImagenAdministrativo = Admin.getImagenURL();
        this.CorreoAdministrativo = Admin.getCorreoUsuario();
        this.CedulaAdministrativo = String.valueOf(Admin.getCedulaUsuario());
    }    
    
}
