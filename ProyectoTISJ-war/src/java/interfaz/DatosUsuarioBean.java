
package interfaz;

import Enumerados.EstadoCivil.EstadoCivil;
import Enumerados.FacadeEnumerados;
import Estudiante.EnumSexo;
import Usuario.FacadeUsuario;
import Utilidades.Cedula;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    private String ApellidoUsuario;
    private String CorreoUsuario;
    private String PasswordUsuario;
    private String ImagenUsuario;
    private String CedulaUsuario;
    private String CredencialCivicaUsuario;
    private String DomicilioUsuario;
    private String DepartamentoUsuario;
    private String LocalidadUsuario;
    private String TelefonoUsuario;
    private String CelularUsuario;
    protected EstadoCivil EstadoCivilUsuario;
    protected Date FechaNacimientoUsuario;
    protected String LugarNacimientoUsuario;
    protected EnumSexo SexoUsuario;
    private String Rol;
    private Part PartImagenFormInscripcion;
    private Part PartImagenPerfil;
    private List<EstadoCivil> ListEstadoCivil;
    private EstadoCivil EstadoCivilSeleccionado;
    
    @EJB
    private FacadeUsuario fUsr;
    
    @EJB
    private FileUpload fUp;
    
    @EJB
    private Cedula verifCedula;
    
    @EJB
    private FacadeEnumerados fEnum;
    
    public DatosUsuarioBean() {}
    
    /*  Setters */
    public void setNombreUsuario(String NombreUsuario) {this.NombreUsuario = NombreUsuario;}
    public void setApellidoUsuario(String ApellidoUsuario) {this.ApellidoUsuario = ApellidoUsuario;}
    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public void setImagenUsuario(String ImagenUsuario) {this.ImagenUsuario = ImagenUsuario;}
    public void setCedulaUsuario(String CedulaUsuario) {this.CedulaUsuario = CedulaUsuario;}
    public void setCredencialCivicaUsuario(String CredencialCivicaUsuario) {this.CredencialCivicaUsuario = CredencialCivicaUsuario;}
    public void setDomicilioUsuario(String DomicilioUsuario) {this.DomicilioUsuario = DomicilioUsuario;}
    public void setDepartamentoUsuario(String DepartamentoUsuario) {this.DepartamentoUsuario = DepartamentoUsuario;}
    public void setLocalidadUsuario(String LocalidadUsuario) {this.LocalidadUsuario = LocalidadUsuario;}
    public void setTelefonoUsuario(String TelefonoUsuario) {this.TelefonoUsuario = TelefonoUsuario;}
    public void setCelularUsuario(String CelularUsuario) {this.CelularUsuario = CelularUsuario;}
    public void setEstadoCivilUsuario(EstadoCivil EstadoCivilUsuario) {this.EstadoCivilUsuario = EstadoCivilUsuario;}
    public void setFechaNacimientoUsuario(Date FechaNacimientoUsuario) {this.FechaNacimientoUsuario = FechaNacimientoUsuario;}
    public void setLugarNacimientoUsuario(String LugarNacimientoUsuario) {this.LugarNacimientoUsuario = LugarNacimientoUsuario;}
    public void setSexoUsuario(EnumSexo SexoUsuario) {this.SexoUsuario = SexoUsuario;}
    public void setRol(String Rol) {this.Rol = Rol;}
    public void setPartImagenFormInscripcion(Part PartImagenFormInscripcion) {this.PartImagenFormInscripcion = PartImagenFormInscripcion;}
    public void setListEstadoCivil(List<EstadoCivil> ListEstadoCivil) {this.ListEstadoCivil = ListEstadoCivil;}
    public void setEstadoCivilSeleccionado(EstadoCivil EstadoCivilSeleccionado) {this.EstadoCivilSeleccionado = EstadoCivilSeleccionado;}
    public void setPartImagenPerfil(Part PartImagenPerfil) {this.PartImagenPerfil = PartImagenPerfil;}
    
    /*  Getters */
    public String getNombreUsuario() {return NombreUsuario;}
    public String getApellidoUsuario() {return ApellidoUsuario;}
    public String getCorreoUsuario() {return CorreoUsuario;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public String getImagenUsuario() {return ImagenUsuario;}
    public String getCedulaUsuario() {return CedulaUsuario;}
    public String getCredencialCivicaUsuario() {return CredencialCivicaUsuario;}
    public String getDomicilioUsuario() {return DomicilioUsuario;}
    public String getDepartamentoUsuario() {return DepartamentoUsuario;}
    public String getLocalidadUsuario() {return LocalidadUsuario;}
    public String getTelefonoUsuario() {return TelefonoUsuario;}
    public String getCelularUsuario() {return CelularUsuario;}
    public EstadoCivil getEstadoCivilUsuario() {return EstadoCivilUsuario;}
    public Date getFechaNacimientoUsuario() {return FechaNacimientoUsuario;}
    public String getLugarNacimientoUsuario() {return LugarNacimientoUsuario;}
    public EnumSexo getSexoUsuario() {return SexoUsuario;}
    public String getRol() {return Rol;}
    public List<EstadoCivil> getListEstadoCivil() {return ListEstadoCivil;}
    public EstadoCivil getEstadoCivilSeleccionado() {return EstadoCivilSeleccionado;}
    public Part getPartImagenPerfil() {return PartImagenPerfil;}
    
    /*  Solo Estudiante   */
    public Part getPartImagenFormInscripcion() {return PartImagenFormInscripcion;}
    
    /**
     * Registra un usuario del segun el rol seleccionado.
     * Sino se selecciono imagen de perfil se registra con imagen por defecto.
     * @return
     */
    public String registrarUsuario(){
        FacesContext context = FacesContext.getCurrentInstance();
        if (verifCedula.EsCedulaValida(CedulaUsuario)) {
            String ubicacionPerfil = fUp.guardarArchivo("ImagenesPerfil", PartImagenPerfil, CedulaUsuario);
            if (Rol.equals("Estudiante")) {
                String ubicacionFrmInscripcion = fUp.guardarArchivo("frmInscripcion", PartImagenFormInscripcion, String.valueOf(CedulaUsuario));
                if (ubicacionFrmInscripcion!=null) {
                    if (ubicacionPerfil!=null) {
                        if (fUsr.RegistrarUsuario(ubicacionFrmInscripcion, NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ubicacionFrmInscripcion, Integer.valueOf(CedulaUsuario),
                                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilSeleccionado,
                                FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario)!=-1) {
                            return "registrado";
                        }
                    }else{
                        if (fUsr.RegistrarUsuario(ubicacionFrmInscripcion, NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, "", Integer.valueOf(CedulaUsuario),
                                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilSeleccionado,
                                FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario)!=-1) {
                            return "registrado";
                        }
                    }
                }else{
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono formulario de inscripcion");
                    context.addMessage("frmIngresoDatos:inputFormIngreso", fm);
                    return "";
                }
            }else{
                if (ubicacionPerfil!=null) {
                    if(fUsr.RegistrarUsuario(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ubicacionPerfil, Integer.valueOf(CedulaUsuario),
                            CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CedulaUsuario, EstadoCivilSeleccionado,
                            FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario, Rol)!=-1){
                        return "registrado";
                    }
                }else{
                    if(fUsr.RegistrarUsuario(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, "", Integer.valueOf(CedulaUsuario),
                            CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CedulaUsuario, EstadoCivilSeleccionado,
                            FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario, Rol)!=-1){
                        return "registrado";
                    }
                }
            }
        }
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula no es valida.");
        context.addMessage("frmIngresoDatos:inputCedula", fm);
        return "";
    }
    
    @PostConstruct
    private void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Rol = request.getParameter("rol");
        
        this.ListEstadoCivil = fEnum.ListarEstadosCiviles();
        this.EstadoCivilSeleccionado = this.ListEstadoCivil.get(0);
    }
}

