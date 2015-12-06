package interfaz;

import Asignatura.Curso.Encuesta.FacadeEncuesta;
import Asignatura.Curso.Encuesta.Pregunta.EnumTipoPregunta;
import Enumerados.FacadeEnumerados;
import Usuario.Estudiante.EnumSexo;
import Usuario.FacadeUsuario;
import Usuario.Usuario;
import java.io.IOException;
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
    
    @EJB
    private FacadeEncuesta fEncuesta;
    
    //  Constructor
    public Login() {}
    
    //  Getters
    public String getRolSeleccionado() {return RolSeleccionado;}
    public String getCedulaUsuario() {return CedulaUsuario;}
    public String getPassword() {return Password;}
    public List<String> getRoles() {return Roles;}
    public List<String> getRolesUsuario() {return rolesUsuario;}
    public int getIdUsuarioLogueado(){
        return fUsr.ValidarLogin(Integer.valueOf(CedulaUsuario), Password, RolSeleccionado);
    }
    //  Setters
    public void setRolSeleccionado(String RolSeleccionado) {this.RolSeleccionado = RolSeleccionado;}
    public void setCedulaUsuario(String CedulaUsuario) {this.CedulaUsuario = CedulaUsuario;}
    public void setPassword(String Password) {this.Password = Password;}
    public void setRoles(List<String> Roles) {this.Roles = Roles;}
    public void setUsuarioLogueado(boolean UsuarioLogueado) {this.UsuarioLogueado = UsuarioLogueado;}
    public void setRolesUsuario(List<String> rolesUsuario) {this.rolesUsuario = rolesUsuario;}
    
    //  Otras
    public boolean isUsuarioLogueado() {return UsuarioLogueado;}
    public int cantidadRoles(){return this.rolesUsuario.size();}
    
    /**
     * Realiza el login del usuario. Si tiene un solo rol se loguea automaticamente, sino redirige a otra pagina para seleccionar el rol.
     */
    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            rolesUsuario = fUsr.ValidarLogin(Integer.valueOf(CedulaUsuario), Password);
            if (rolesUsuario.isEmpty()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Los datos ingresados no son correctos");
                context.addMessage("login:msj", fm);
            }else{
                if (rolesUsuario.size()==1) {
                    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                    Usuario Usr = fUsr.BuscarUsuario(fUsr.ValidarLogin(Integer.valueOf(CedulaUsuario), Password, rolesUsuario.get(0)));
                    request.getSession().setAttribute("Usuario", Usr);
                    this.UsuarioLogueado = true;
                    this.RolSeleccionado = rolesUsuario.get(0);
                    context.getExternalContext().redirect("Views/index.xhtml");
                }else{
                    LlenarRoles(this.rolesUsuario);
                    context.getExternalContext().redirect("loginRol.xhtml");
                }
            }
        }catch(NumberFormatException | IOException | NullPointerException ex){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Los datos ingresados no son correctos");
            context.addMessage("login:msj", fm);
        }
    }
    
    /**
     * Completa el login con un rol seleccionado.
     * @param RolSeleccionado
     * @throws java.io.IOException
     */
    public void loginRol(String RolSeleccionado) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        this.RolSeleccionado = RolSeleccionado;
        Usuario Usr = fUsr.BuscarUsuario(fUsr.ValidarLogin(Integer.valueOf(CedulaUsuario), Password, RolSeleccionado));
        if (Usr!=null) {
            request.getSession().setAttribute("Usuario", Usr);
            this.UsuarioLogueado = true;
            request.getSession().setAttribute("Usuario", Usr);
            context.getExternalContext().redirect("Views/index.xhtml");
        }
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Datos incorrectos");
        context.addMessage("login:msj", fm);
    }
    
    /**
     * Cambia el rol del usuario ya logueado.
     * @param RolSeleccionado
     * @throws java.io.IOException
     */
    public void cambiarRol(String RolSeleccionado) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        if(request.getSession().getAttribute("Usuario")!= null){
            this.RolSeleccionado = RolSeleccionado;
            context.getExternalContext().redirect("Views/index.xhtml");
        }
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Datos incorrectos");
        context.addMessage("login:msj", fm);
        context.getExternalContext().redirect("Views/index.xhtml");
    }
    
    /**
     *
     * @throws java.io.IOException
     */
    public void logout() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.getSession().invalidate();
        this.UsuarioLogueado = false;
        context.getExternalContext().redirect("../login.xhtml");
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
        /*
        Estado Civil
        */
        try{
            fEnum.crearEstadoCivil("Soltero");
            fEnum.crearEstadoCivil("Casado");
            fEnum.crearEstadoCivil("Divorciado");
        }catch(Exception ex){}
        
        /*
        Estudios Cursados
        */
        try{
            fEnum.crearTipoDeEstudio("Universidad");
            fEnum.crearTipoDeEstudio("UTU");
            fEnum.crearTipoDeEstudio("Secundaria");
            fEnum.crearTipoDeEstudio("Formacion Docente");
            fEnum.crearTipoDeEstudio("Estudios Militares");
        }catch(Exception ex){}
        
        /*
        Usuarios
        */
        Date fNac;
        Calendar cal = Calendar.getInstance();
        // mes de 0 a 11
        cal.set(1990, 1-1, 1);
        fNac = cal.getTime();
        int id = fUsr.RegistrarUsuario("Administrador", "ApellidoAdmin", "Admin@administrador.edu.uy", "1234", "", 12345672, "ABC 1234", "Calle 1234",
                "Departamento", "Localidad", "1234 1234", "09123456", fEnum.ListarEstadosCiviles().get(0), fNac, "Lugar de Nacimiento",
                EnumSexo.Masculino, "Administrador", 0);
        fUsr.RegistrarUsuario("Administrador", "ApellidoAdmin", "Admin@administrador.edu.uy", "1234", "", 12345672, "ABC 1234", "Calle 1234",
                "Departamento", "Localidad", "1234 1234", "09123456", fEnum.ListarEstadosCiviles().get(0), fNac, "Lugar de Nacimiento",
                EnumSexo.Masculino, "Docente", id);
        
        /*
        Preguntas Docente
        */
        try{
            fEncuesta.CrearPregunta("Marca los objetivos específicos de cada clase.", EnumTipoPregunta.Docente);
            fEncuesta.CrearPregunta("Explica en clase con órden y claridad.", EnumTipoPregunta.Docente);
            fEncuesta.CrearPregunta("Define el vocabulario especializado o técnico que utiliza.", EnumTipoPregunta.Docente);
            fEncuesta.CrearPregunta("Sintetiza y subraya los conceptos que considera importantes.", EnumTipoPregunta.Docente);
            fEncuesta.CrearPregunta("Establece conexiones con los contenidos de otras asignaturas, presenta ejemplos aplicados a la vida profesional y/o a la vida cotidiana.", EnumTipoPregunta.Docente);
            fEncuesta.CrearPregunta("Cuando usa el pizarrón, transparencias, videos, etc., lo hace adecuadamente, ayudando a comprender mejor las explicaciones.", EnumTipoPregunta.Docente);
            fEncuesta.CrearPregunta("Favorece el planteo de preguntas y se preocupa por responderlas.", EnumTipoPregunta.Docente);
            fEncuesta.CrearPregunta("Motiva al estudiante por la asignatura.", EnumTipoPregunta.Docente);
            fEncuesta.CrearPregunta("Cumple con los horarios de clase.", EnumTipoPregunta.Docente);
            fEncuesta.CrearPregunta("Tiene una actitud respetuosa hacia los estudiantes.", EnumTipoPregunta.Docente);
            fEncuesta.CrearPregunta("Juicio global sobre el docente.", EnumTipoPregunta.Docente);
        }catch(Exception ex){}
        /*
        Preguntas Curso
        */
        
        try{
            fEncuesta.CrearPregunta("Son útiles las clases de consulta (presenciales, e-mail y/o foros de discusión)", EnumTipoPregunta.Curso);
            fEncuesta.CrearPregunta("Se recomiendan y utilizan materiales de estudio útiles para preparar la asignatura.", EnumTipoPregunta.Curso);
            fEncuesta.CrearPregunta("Le resulta útil la página web del curso.", EnumTipoPregunta.Curso);
            fEncuesta.CrearPregunta("Existe coordinación entre lo dictado en clases teóricas y prácticas.", EnumTipoPregunta.Curso);
            fEncuesta.CrearPregunta("La propuesta de evaluación realizada fue clara y sin ambigüedad.", EnumTipoPregunta.Curso);
            fEncuesta.CrearPregunta("La evaluación propuesta se puede realizar con los conociemientos que se han impartido durante el curso.", EnumTipoPregunta.Curso);
            fEncuesta.CrearPregunta("La evaluación se centró en la comprensión de la asignatura.", EnumTipoPregunta.Curso);
            fEncuesta.CrearPregunta("Juicio global sobre el curso.", EnumTipoPregunta.Curso);
        }catch(Exception ex){}
        
    }
    
}
