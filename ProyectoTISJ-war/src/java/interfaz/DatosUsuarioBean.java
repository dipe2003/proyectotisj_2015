
package interfaz;

import Usuario.FacadeUsuario;
import Utilidades.Cedula;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class DatosUsuarioBean implements Serializable{

    private String NombreUsuario; 
    private Part ImagenUsuario;
    private String CorreoUsuario;
    private String CedulaUsuario;
    private String PasswordUsuario;
    private String Rol;
    private Part PartImagenFormInscripcion;
    
    @EJB
    private FacadeUsuario fUsr;
    
    @EJB
    private FileUpload fUp;
    
    @EJB
    private Cedula verifCedula;
    
    public DatosUsuarioBean() {}

    public String getNombreUsuario() {return NombreUsuario;}

    public void setNombreUsuario(String NombreUsuario) {this.NombreUsuario = NombreUsuario;}

    public Part getImagenUsuario() {return ImagenUsuario;}

    public void setImagenUsuario(Part ImagenUsuario) {this.ImagenUsuario = ImagenUsuario;}

    public String getCorreoUsuario() {return CorreoUsuario;}

    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}

    public String getCedulaUsuario() {return CedulaUsuario;}

    public void setCedulaUsuario(String CedulaUsuario) {this.CedulaUsuario = CedulaUsuario;}

    public String getPasswordDocente() {return PasswordUsuario;}

    public void setPasswordDocente(String PasswordDocente) {this.PasswordUsuario = PasswordDocente;}

    public String getRol() {return Rol;}

    public void setRol(String Rol) {this.Rol = Rol;}
    
    /*
    Solo Estudiante
    */

    public Part getPartImagenFormInscripcion() {return PartImagenFormInscripcion;}

    public void setPartImagenFormInscripcion(Part PartImagenFormInscripcion) {this.PartImagenFormInscripcion = PartImagenFormInscripcion;}
    
               
    public String registrarUsuario(){        
        if (verifCedula.EsCedulaValida(CedulaUsuario)) {
            String ubicacion = fUp.guardarArchivo("ImagenesPerfil", ImagenUsuario, CedulaUsuario);
            if (ubicacion!=null) {
                if(fUsr.RegistrarUsuario(NombreUsuario, CorreoUsuario, PasswordUsuario, Integer.valueOf(CedulaUsuario), Rol, "" ,ubicacion )!=-1){
                    return "registrado";
                }
            }else{
                if(fUsr.RegistrarUsuario(NombreUsuario, CorreoUsuario, PasswordUsuario, Integer.valueOf(CedulaUsuario), Rol, "" ,"" )!=-1){
                    return "registrado";
                }                
            }
        }        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula no es valida.");
        context.addMessage("frmIngresoDatos:inputCedula", fm);
        return "";
    }
    
    @PostConstruct
    private void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Rol = request.getParameter("rol");
    }
}

