package interfaz;

import Enumerados.FacadeEnumerados;
import Estudiante.EnumSexo;
import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


@Named("login")
@SessionScoped
public class Login implements Serializable {
    private String CedulaUsuario;
    private String Password;
    private List<String> Roles;
    private String RolSeleccionado;
    private boolean UsuarioLogueado;
    List<String> rolesUsuario;
    
    @EJB
    private FacadeEnumerados fEnum;
    
    @EJB
    private FacadeUsuario fUsr;
    
    //  Constructor
    public Login() {}
    
    //  Setters
    public void setRolSeleccionado(String RolSeleccionado) {this.RolSeleccionado = RolSeleccionado;}
    public void setCedulaUsuario(String CedulaUsuario) {this.CedulaUsuario = CedulaUsuario;}
    public void setPassword(String Password) {this.Password = Password;}
    public void setRoles(List<String> Roles) {this.Roles = Roles;}
    public void setUsuarioLogueado(boolean UsuarioLogueado) {this.UsuarioLogueado = UsuarioLogueado;}
    public void setRolesUsuario(List<String> rolesUsuario) {this.rolesUsuario = rolesUsuario;}
    
    //  Getters
    public String getRolSeleccionado() {return RolSeleccionado;}
    public String getCedulaUsuario() {return CedulaUsuario;}
    public String getPassword() {return Password;}
    public List<String> getRoles() {return Roles;}
    public List<String> getRolesUsuario() {return rolesUsuario;}
    
    //  Otras
    public boolean isUsuarioLogueado() {return UsuarioLogueado;}
    public int cantidadRoles(){return this.rolesUsuario.size();}
    
    /**
     * Realiza el login del usuario. Si tiene un solo rol se loguea automaticamente, sino redirige a otra pagina para seleccionar el rol.
     * @return
     */
    public String login(){
        FacesContext context = FacesContext.getCurrentInstance();
        rolesUsuario = fUsr.ValidarLogin(Integer.valueOf(CedulaUsuario), Password);
        if (rolesUsuario.isEmpty()) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Los datos ingresados no son correctos");
            context.addMessage("login:msj", fm);
            return null;
        }else{
            if (rolesUsuario.size()==1) {
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                Usuario Usr = fUsr.BuscarUsuario(fUsr.ValidarLogin(Integer.valueOf(CedulaUsuario), Password, rolesUsuario.get(0)));
                request.getSession().setAttribute("Usuario", Usr);
                this.UsuarioLogueado = true;
                this.RolSeleccionado = rolesUsuario.get(0);
                return "logueado";
            }else{
                LlenarRoles(this.rolesUsuario);
                return "seleccionarRol";
            }
        }
    }
    
    /**
     * Completa el login con un rol seleccionado.
     * @param RolSeleccionado
     * @return
     */
    public String loginRol(String RolSeleccionado){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        this.RolSeleccionado = RolSeleccionado;
        Usuario Usr = fUsr.BuscarUsuario(fUsr.ValidarLogin(Integer.valueOf(CedulaUsuario), Password, RolSeleccionado));
        if (Usr!=null) {
            request.getSession().setAttribute("Usuario", Usr);
            this.UsuarioLogueado = true;
            return "logueado";
        }
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Datos incorrectos");
        context.addMessage("login:msj", fm);
        return "";
    }
    
    /**
     * Cambia el rol del usuario ya logueado.
     * @param RolSeleccionado
     * @return 
     */
    public String cambiarRol(String RolSeleccionado){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        if(request.getSession().getAttribute("Usuario")!= null){
            this.RolSeleccionado = RolSeleccionado;
            return "logueado";
        }
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Datos incorrectos");
        context.addMessage("login:msj", fm);
        return "";
    }
    
    /**
     *
     * @return
     */
    public String logout(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.getSession().invalidate();
        this.UsuarioLogueado = false;
        return "nologueado";
    }
    
    /**
     * Llena la lista de roles de usuario para utilizarlos en la seleccion en LoginRol.
     * @param RolesUsuario
     */
    private void LlenarRoles(List<String> RolesUsuario){
        for (int i = 0; i < RolesUsuario.size(); i++) {
            this.Roles.add(RolesUsuario.get(i));
        }
        RolSeleccionado = "";
    }
    
    @PostConstruct
    public void Init(){
        this.rolesUsuario = new ArrayList<>();
        this.Roles = new ArrayList<>();
        
        try{
            fEnum.crearEstadoCivil("Soltero");
            fEnum.crearEstadoCivil("Casado");
            fEnum.crearEstadoCivil("Divorciado");
        }catch(Exception ex){}
        
        try{
            fEnum.crearTipoDeEstudio("Universidad");
            fEnum.crearTipoDeEstudio("UTU");
            fEnum.crearTipoDeEstudio("Secundaria");
            fEnum.crearTipoDeEstudio("Formacion Docente");
            fEnum.crearTipoDeEstudio("Estudios Militares");
        }catch(Exception ex){}
        
        Date fNac;
        Calendar cal = Calendar.getInstance();
        // mes de 0 a 11
        cal.set(1990, 1-1, 1);
        fNac = cal.getTime();
        fUsr.RegistrarUsuario("Administrador", "ApellidoAdmin", "Admin@administrador.edu.uy", "1234", "", 12345672, "ABC 1234", "Calle 1234",
                "Departamento", "Localidad", "1234 1234", "09123456", fEnum.ListarEstadosCiviles().get(0), fNac, "Lugar de Nacimiento",
                EnumSexo.Masculino, "Administrador");
        fUsr.RegistrarUsuario("Docente", "ApellidoDocente", "Docente@administrador.edu.uy", "1234", "", 12345672, "ABC 1234", "Calle 1234",
                "Departamento", "Localidad", "1234 1234", "09123456", fEnum.ListarEstadosCiviles().get(0), fNac, "Lugar de Nacimiento",
                EnumSexo.Masculino, "Docente");
    }
    
}
