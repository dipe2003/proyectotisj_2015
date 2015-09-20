
package interfaz.estudiante;

import Estudiante.EnumEstadoCivil;
import Estudiante.EnumSexo;
import interfaz.*;
import Usuario.FacadeUsuario;
import Utilidades.Cedula;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class DatosEstudianteBean implements Serializable{
    
    private String NombreEstudiante;
    private Part ImagenEstudiante;
    private String CorreoEstudiante;
    private String CedulaEstudiante;
    private String PasswordEstudiante;
    private Part PartImagenFormInscripcion;
    private EnumSexo SexoEstudiante;
    private String LugarNcimientoEstudiante;
    private Date FechaNacimientoEstudiante;
    private String LugarNacimietoEstudiante;
    private EnumEstadoCivil EstadoCivilEstudiante;
    private String CredencialCivicaEstudiante;
    private String DomicilioEstudiante;
    private String DepartamentoEstudiante;
    private String LocalidadEstudiante;
    private String TelefonoEstudiante;
    private String CelularEstudiante;
    
    @EJB
    private FacadeUsuario fUsr;
    
    @EJB
    private FileUpload fUp;
    
    @EJB
    private Cedula verifCedula;
    
    public DatosEstudianteBean() {}
    
    public String getNombreEstudiante() {return NombreEstudiante;}
    
    public void setNombreEstudiante(String NombreEstudiante) {this.NombreEstudiante = NombreEstudiante;}
    
    public Part getImagenEstudiante() {return ImagenEstudiante;}
    
    public void setImagenEstudiante(Part ImagenEstudiante) {this.ImagenEstudiante = ImagenEstudiante;}
    
    public String getCorreoEstudiante() {return CorreoEstudiante;}
    
    public void setCorreoEstudiante(String CorreoEstudiante) {this.CorreoEstudiante = CorreoEstudiante;}
    
    public String getCedulaEstudiante() {return CedulaEstudiante;}
    
    public void setCedulaEstudiante(String CedulaEstudiante) {this.CedulaEstudiante = CedulaEstudiante;}
    
    public String getPasswordEstudiante() {return PasswordEstudiante;}
    
    public void setPasswordEstudiante(String PasswordDocente) {this.PasswordEstudiante = PasswordDocente;}
    
    public Part getPartImagenFormInscripcion() {return PartImagenFormInscripcion;}
    
    public void setPartImagenFormInscripcion(Part PartImagenFormInscripcion) {this.PartImagenFormInscripcion = PartImagenFormInscripcion;}
    
    public EnumSexo getSexoEstudiante() {return SexoEstudiante;}
    
    public void setSexoEstudiante(EnumSexo SexoEstudiante) {this.SexoEstudiante = SexoEstudiante;}
    
    public String getLugarNcimientoEstudiante() {return LugarNcimientoEstudiante;}
    
    public void setLugarNcimientoEstudiante(String LugarNcimientoEstudiante) {this.LugarNcimientoEstudiante = LugarNcimientoEstudiante;}
    
    public Date getFechaNacimientoEstudiante() {return FechaNacimientoEstudiante;}
    
    public void setFechaNacimientoEstudiante(Date FechaNacimientoEstudiante) {this.FechaNacimientoEstudiante = FechaNacimientoEstudiante;}
    
    public String getLugarNacimietoEstudiante() {return LugarNacimietoEstudiante;}
    
    public void setLugarNacimietoEstudiante(String LugarNacimietoEstudiante) {this.LugarNacimietoEstudiante = LugarNacimietoEstudiante;}
    
    public EnumEstadoCivil getEstadoCivilEstudiante() {return EstadoCivilEstudiante;}
    
    public void setEstadoCivilEstudiante(EnumEstadoCivil EstadoCivilEstudiante) {this.EstadoCivilEstudiante = EstadoCivilEstudiante;}
    
    public String getCredencialCivicaEstudiante() {return CredencialCivicaEstudiante;}
    
    public void setCredencialCivicaEstudiante(String CredencialCivicaEstudiante) {this.CredencialCivicaEstudiante = CredencialCivicaEstudiante;}
    
    public String getDomicilioEstudiante() {return DomicilioEstudiante;}
    
    public void setDomicilioEstudiante(String DomicilioEstudiante) {this.DomicilioEstudiante = DomicilioEstudiante;}
    
    public String getDepartamentoEstudiante() {return DepartamentoEstudiante;}
    
    public void setDepartamentoEstudiante(String DepartamentoEstudiante) {this.DepartamentoEstudiante = DepartamentoEstudiante;}
    
    public String getLocalidadEstudiante() {return LocalidadEstudiante;}
    
    public void setLocalidadEstudiante(String LocalidadEstudiante) {this.LocalidadEstudiante = LocalidadEstudiante;}
    
    public String getTelefonoEstudiante() {return TelefonoEstudiante;}
    
    public void setTelefonoEstudiante(String TelefonoEstudiante) {this.TelefonoEstudiante = TelefonoEstudiante;}
    
    public String getCelularEstudiante() {return CelularEstudiante;}
    
    public void setCelularEstudiante(String CelularEstudiante) {this.CelularEstudiante = CelularEstudiante;}
    
    /**
     * Registra un estudiante.
     * Comprueba que se haya seleccionado formulario de ingreso (necesario).
     * Sino se selecciono imagen de perfil se registra con imagen por defecto.
     * @return 
     */
    public String registrarEstudiante(){
        if (verifCedula.EsCedulaValida(CedulaEstudiante)) {
            String ubicacionPerfil = fUp.guardarArchivo("ImagenesPerfil", ImagenEstudiante, CedulaEstudiante);
            String ubicacionFrmIngreso = fUp.guardarArchivo("FrmInscripcion", PartImagenFormInscripcion, CedulaEstudiante);
            if (ubicacionFrmIngreso!=null) {
                if (ubicacionPerfil!=null) {
                    if(fUsr.RegistrarUsuario(SexoEstudiante, LugarNcimientoEstudiante, FechaNacimientoEstudiante, LugarNacimietoEstudiante,
                            EstadoCivilEstudiante, CredencialCivicaEstudiante, DomicilioEstudiante, DepartamentoEstudiante, LocalidadEstudiante, TelefonoEstudiante,
                            CelularEstudiante, ubicacionFrmIngreso, NombreEstudiante, CorreoEstudiante, PasswordEstudiante, Integer.valueOf(CedulaEstudiante),  ubicacionPerfil)!=-1){
                        return "registrado";
                    }
                }else{
                    if(fUsr.RegistrarUsuario(SexoEstudiante, LugarNcimientoEstudiante, FechaNacimientoEstudiante, LugarNacimietoEstudiante,
                            EstadoCivilEstudiante, CredencialCivicaEstudiante, DomicilioEstudiante, DepartamentoEstudiante, LocalidadEstudiante, TelefonoEstudiante,
                            CelularEstudiante, ubicacionFrmIngreso, NombreEstudiante, CorreoEstudiante, PasswordEstudiante, Integer.valueOf(CedulaEstudiante),  "")!=-1){
                        return "registrado";
                    }
                }
            }else{
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se selecciono formulario de inscripcion");
                context.addMessage("frmIngresoDatos:inputFormIngreso", fm);
                return "";
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "La Cedula no es valida.");
        context.addMessage("frmIngresoDatos:inputCedula", fm);
        return "";
    }
}

