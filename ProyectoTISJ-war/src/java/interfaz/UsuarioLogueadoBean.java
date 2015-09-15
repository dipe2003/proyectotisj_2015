
package interfaz;

import Usuario.FacadeUsuario;
import Usuario.Usuario;
import Utilidades.Cedula;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@Named("usuarioLogueadoBean")
@SessionScoped
public class UsuarioLogueadoBean implements Serializable{
    
    private String CedulaUsuario;
    private String NombreUsuario;
    private String ImagenUsuario;
    private String CorreoUsuario;
    private Part PartImagenUsuario;
    private String RolSeleccionado;
    private Part PartImagenFormInscripcion;
    
    @Inject
    private Login login;
    
    @EJB
    private FacadeUsuario fUser;
    
    @EJB
    private Cedula CI;
    
    @EJB
    private FileUpload fUp;
    
    public UsuarioLogueadoBean() {}
    
    public String getNombreUsuario() {return NombreUsuario;}
    
    public void setNombreUsuario(String NombreUsuario) {this.NombreUsuario = NombreUsuario;}
    
    public String getImagenUsuario() {return ImagenUsuario;}
    
    public void setImagenUsuario(String ImagenUsuario) {this.ImagenUsuario = ImagenUsuario;}
    
    public String getCorreoUsuario() {return CorreoUsuario;}
    
    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}
    
    public String getCedulaUsuario() {return CedulaUsuario;}
    
    public void setCedulaUsuario(String CedulaUsuario) {this.CedulaUsuario = CedulaUsuario;}
    
    public Part getPartImagenUsuario() {return PartImagenUsuario;}
    
    public void setPartImagenUsuario(Part PartImagenUsuario) {this.PartImagenUsuario = PartImagenUsuario;}
    
    /*
    Solo Estudiante
    */

    public Part getPartImagenFormInscripcion() {return PartImagenFormInscripcion;}

    public void setPartImagenFormInscripcion(Part PartImagenFormInscripcion) {this.PartImagenFormInscripcion = PartImagenFormInscripcion;}
    

    @PostConstruct
    public void Init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Usuario  User = (Usuario)request.getSession().getAttribute("Usuario");
        this.NombreUsuario = User.getNombreUsuario();
        this.ImagenUsuario = User.getImagenURL();
        this.CorreoUsuario = User.getCorreoUsuario();
        this.CedulaUsuario = String.valueOf(User.getCedulaUsuario());
        this.RolSeleccionado = login.getRolSeleccionado();
    }
    
    public void modificarUsuario(String actualPass, String newPass, String confirmPass){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Usuario  User = (Usuario)request.getSession().getAttribute("Usuario");
        
        if (!CI.EsCedulaValida(CedulaUsuario)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula no es valida.");
            context.addMessage("frmIngresoDatos:inputCedula", fm);
        }else{
            if (!actualPass.isEmpty() || !actualPass.equals("")) {
                if (!User.isValidPass(actualPass)) {
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
                            String ubicacion = fUp.guardarArchivo("ImagenesPerfil", PartImagenUsuario, CedulaUsuario);
                            if (ubicacion!=null) {
                                User.setImagenURL(this.ImagenUsuario);
                            }
                            guardarModificacion(User, newPass);
                        }
                    }
                }
            }else{
                String ubicacion = fUp.guardarArchivo("ImagenesPerfil", PartImagenUsuario, CedulaUsuario);
                if (ubicacion!=null) {
                    User.setImagenURL(ubicacion);
                }
                guardarModificacion(User, User.getPasswordUsuario());
            }
        }
    }
    
    private void guardarModificacion(Usuario User, String newPass){
        User.setCedulaUsuario(Integer.valueOf(this.CedulaUsuario));
        User.setNombreUsuario(this.NombreUsuario);
        User.setPasswordUsuario(newPass);
        User.setCorreoUsuario(this.CorreoUsuario);
        fUser.ModificarUsuario(User, RolSeleccionado);
        ImagenUsuario = User.getImagenURL();
    }
}
