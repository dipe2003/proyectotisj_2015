
package interfaz.Administrador;

import Administrador.Administrador;
import Administrador.FacadeAdministrador;
import Utilidades.Cedula;
import interfaz.FileUpload;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@Named("administradorBean")
@SessionScoped
public class AdministradorBean implements Serializable{
    
    private String CedulaAdministrador;
    private String NombreAdministrador;
    private String ImagenAdministrador;
    private String CorreoAdministrador;
    private Part PartImagenAdministrador;
    
    @EJB
    private FacadeAdministrador fAdmin;
    
    @EJB
    private Cedula CI;
    
    @EJB
    private FileUpload fUp;
    
    public AdministradorBean() {}
    
    public String getNombreAdministrador() {return NombreAdministrador;}
    
    public void setNombreAdministrador(String NombreAdministrador) {this.NombreAdministrador = NombreAdministrador;}
    
    public String getImagenAdministrador() {return ImagenAdministrador;}
    
    public void setImagenAdministrador(String ImagenAdministrador) {this.ImagenAdministrador = ImagenAdministrador;}
    
    public String getCorreoAdministrador() {return CorreoAdministrador;}
    
    public void setCorreoAdministrador(String CorreoAdministrador) {this.CorreoAdministrador = CorreoAdministrador;}
    
    public String getCedulaAdministrador() {return CedulaAdministrador;}
    
    public void setCedulaAdministrador(String CedulaAdministrador) {this.CedulaAdministrador = CedulaAdministrador;}
    
    public Part getPartImagenAdministrador() {return PartImagenAdministrador;}
    
    public void setPartImagenAdministrador(Part PartImagenAdministrador) {this.PartImagenAdministrador = PartImagenAdministrador;}
    
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
    
    public void modificarAdministrador(String actualPass, String newPass, String confirmPass){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Administrador  Admin = (Administrador)request.getSession().getAttribute("Administrador");
        
        if (!CI.EsCedulaValida(CedulaAdministrador)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula no es valida.");
            context.addMessage("frmIngresoDatos:inputCedula", fm);
        }else{
            if (!actualPass.isEmpty() || !actualPass.equals("")) {
                if (!Admin.isValidPass(actualPass)) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La contraseña actual no es correcta");
                    context.addMessage("frmIngresoDatos:inputActualPass", fm);
                }else{
                    if (newPass.isEmpty() && confirmPass.isEmpty()) {
                        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La contraseña no puede ser vacia.");
                        context.addMessage("frmIngresoDatos:inputConfirmPass", fm);
                    }else{
                        if (!newPass.equals(confirmPass)) {
                            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Las contraseñas no coinciden");
                            context.addMessage("frmIngresoDatos:inputConfirmPass", fm);
                        }else{
                            String ubicacion = fUp.guardarArchivo("ImagenesPerfil", PartImagenAdministrador, CedulaAdministrador);
                            if (ubicacion!=null) {
                                Admin.setImagenURL(this.ImagenAdministrador);
                            }
                            guardarModificacion(Admin, newPass);
                        }
                    }
                }
            }else{
                String ubicacion = fUp.guardarArchivo("ImagenesPerfil", PartImagenAdministrador, CedulaAdministrador);
                if (ubicacion!=null) {
                    Admin.setImagenURL(ubicacion);
                }
                guardarModificacion(Admin, Admin.getPasswordUsuario());
            }
        }
    }
    
    private void guardarModificacion(Administrador Admin, String newPass){
        Admin.setCedulaUsuario(Integer.valueOf(this.CedulaAdministrador));
        Admin.setNombreUsuario(this.NombreAdministrador);
        Admin.setPasswordUsuario(newPass);
        Admin.setCorreoUsuario(this.CorreoAdministrador);
        fAdmin.ModificarAdministrador(Admin);
        ImagenAdministrador = Admin.getImagenURL();
    }
}
