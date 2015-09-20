
package Estudiante;

import Estudiante.estudios.Estudio;
import Evaluacion.Evaluacion;
import Respuesta.Respuesta;
import Usuario.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Estudiante extends Usuario {
    private EnumSexo SexoEstudiante;
    private String LugarNcimientoEstudiante;
    @Temporal(TemporalType.DATE)
    private Date FechaNacimientoEstudiante;
    private String LugarNacimietoEstudiante;
    private EnumEstadoCivil EstadoCivilEstudiante;
    private String CredencialCivicaEstudiante;
    private String DomicilioEstudiante;
    private String DepartamentoEstudiante;
    private String LocalidadEstudiante;
    private String TelefonoEstudiante;
    private String CelularEstudiante;    
    private String FormInscripcion;
    
    @OneToMany
    private List<Evaluacion> EvaluacionesEstudiante;
    
    @OneToMany
    private List<Respuesta> RespuestasEstudiante;
    
    @OneToMany
    private List<Estudio> EstudiosCursadosEstudiante;

    public Estudiante(EnumSexo SexoEstudiante, String LugarNcimientoEstudiante, Date FechaNacimientoEstudiante, String LugarNacimietoEstudiante, 
            EnumEstadoCivil EstadoCivilEstudiante, String CredencialCivicaEstudiante, String DomicilioEstudiante, String DepartamentoEstudiante, 
            String LocalidadEstudiante, String TelefonoEstudiante, String CelularEstudiante, String FormInscripcion, String NombreUsuario, 
            String CorreoUsuario, String PasswordUsuario, int CedulaUsuario, String ImagenUsuario) {
        super(NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario, ImagenUsuario);
        this.SexoEstudiante = SexoEstudiante;
        this.LugarNcimientoEstudiante = LugarNcimientoEstudiante;
        this.FechaNacimientoEstudiante = FechaNacimientoEstudiante;
        this.LugarNacimietoEstudiante = LugarNacimietoEstudiante;
        this.EstadoCivilEstudiante = EstadoCivilEstudiante;
        this.CredencialCivicaEstudiante = CredencialCivicaEstudiante;
        this.DomicilioEstudiante = DomicilioEstudiante;
        this.DepartamentoEstudiante = DepartamentoEstudiante;
        this.LocalidadEstudiante = LocalidadEstudiante;
        this.TelefonoEstudiante = TelefonoEstudiante;
        this.CelularEstudiante = CelularEstudiante;
        this.FormInscripcion = FormInscripcion;
        this.EstudiosCursadosEstudiante = new ArrayList<>();
        this.EvaluacionesEstudiante = new ArrayList<>();
        this.RespuestasEstudiante = new ArrayList<>();
    }



    public Estudiante() {}

    public String getFormInscripcion() {return FormInscripcion;}

    public void setFormInscripcion(String FormInscripcion) {this.FormInscripcion = FormInscripcion;}

    public List<Evaluacion> getEvaluacionesEstudiante() {return EvaluacionesEstudiante;}

    public void setEvaluacionesEstudiante(List<Evaluacion> EvaluacionesEstudiante) {this.EvaluacionesEstudiante = EvaluacionesEstudiante;}

    public List<Respuesta> getRespuestasEstudiante() {return RespuestasEstudiante;}

    public void setRespuestasEstudiante(List<Respuesta> RespuestasEstudiante) {this.RespuestasEstudiante = RespuestasEstudiante;}

    public void addRespuestaEstudiante(Respuesta RespuestaEstudiante){this.RespuestasEstudiante.add(RespuestaEstudiante);}
    
    public void removeRespuestaEstudiante(Respuesta RespuestaEstudiante){this.RespuestasEstudiante.remove(RespuestaEstudiante);}
    
    public void addEvalucionEstudiante(Evaluacion EvaluacionEstudiante){this.EvaluacionesEstudiante.add(EvaluacionEstudiante);}
    
    public void removeEvaluacionEstudiante(Evaluacion EvaluacionEstudiante){this.EvaluacionesEstudiante.remove(EvaluacionEstudiante);}

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

    public List<Estudio> getEstudiosCursadosEstudiante() {return EstudiosCursadosEstudiante;}

    public void setEstudiosCursadosEstudiante(List<Estudio> EstudiosCursadosEstudiante) {this.EstudiosCursadosEstudiante = EstudiosCursadosEstudiante;}
    
    public void addEstudioCursado(Estudio EstudioCursado){
        if (this.EstudiosCursadosEstudiante == null) {
            this.EstudiosCursadosEstudiante = new ArrayList<>();
        }
        this.EstudiosCursadosEstudiante.add(EstudioCursado);}
    
    public void removeEstudioCursado(Estudio EstudioCursado){this.EstudiosCursadosEstudiante.remove(EstudioCursado);}
    
}
