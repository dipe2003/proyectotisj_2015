
package interfaz;

import Usuario.FacadeUsuario;
import Usuario.Usuario;
import Utilidades.Cedula;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
    
    //  Constructores
    public UsuarioLogueadoBean() {}
    
    //  Getters    
    public String getNombreUsuario() {return NombreUsuario;}
    public String getImagenUsuario() {return ImagenUsuario;}
    public String getCorreoUsuario() {return CorreoUsuario;}
    public String getCedulaUsuario() {return CedulaUsuario;}
    public Part getPartImagenUsuario() {return PartImagenUsuario;}
    public Part getPartImagenFormInscripcion() {return PartImagenFormInscripcion;}
    
    //  Setters    
    public void setNombreUsuario(String NombreUsuario) {this.NombreUsuario = NombreUsuario;}    
    public void setImagenUsuario(String ImagenUsuario) {this.ImagenUsuario = ImagenUsuario;}    
    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}    
    public void setCedulaUsuario(String CedulaUsuario) {this.CedulaUsuario = CedulaUsuario;}
    public void setPartImagenUsuario(Part PartImagenUsuario) {this.PartImagenUsuario = PartImagenUsuario;}
    public void setPartImagenFormInscripcion(Part PartImagenFormInscripcion) {this.PartImagenFormInscripcion = PartImagenFormInscripcion;}
    
    @PostConstruct
    public void Init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Usuario  User = (Usuario)request.getSession().getAttribute("Usuario");
        this.NombreUsuario = User.getNombreUsuario();
        this.ImagenUsuario = User.getImagenUsuario();
        this.CorreoUsuario = User.getCorreoUsuario();
        this.CedulaUsuario = String.valueOf(User.getCedulaUsuario());
        this.RolSeleccionado = login.getRolSeleccionado();        
    }
    
    public void modificarUsuarioLogueado(Usuario usuario){
        this.NombreUsuario = usuario.getNombreUsuario();
        this.ImagenUsuario = usuario.getImagenUsuario();
        this.CorreoUsuario = usuario.getCorreoUsuario();
        this.CedulaUsuario = String.valueOf(usuario.getCedulaUsuario());
        this.RolSeleccionado = login.getRolSeleccionado();
        login.setCedulaUsuario(CedulaUsuario);
    }
    
}
