
package Estudiante;

import Enumerados.EstadoCivil.EstadoCivil;
import Estudiante.estudios.Estudio;
import Evaluacion.Evaluacion;
import Respuesta.Respuesta;
import Usuario.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Estudiante extends Usuario {
    private String FormInscripcion;
    
    @OneToMany
    private List<Evaluacion> EvaluacionesEstudiante;
    
    @OneToMany
    private List<Respuesta> RespuestasEstudiante;
    
    @OneToMany
    private List<Estudio> EstudiosCursadosEstudiante;

    public Estudiante(String FormInscripcion, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, 
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario, 
            String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, 
            String LugarNacimientoUsuario, EnumSexo SexoUsuario) {
        super(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ImagenUsuario, CedulaUsuario, CredencialCivicaUsuario, DomicilioUsuario, 
                DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario, 
                SexoUsuario);
        this.FormInscripcion = FormInscripcion;
        this.FormInscripcion = FormInscripcion;
        this.EvaluacionesEstudiante = new ArrayList<>();
        this.EvaluacionesEstudiante = new ArrayList<>();
        this.EstudiosCursadosEstudiante = new ArrayList<>();    
    }

    public Estudiante() {}
    
    public void setFormInscripcion(String FormInscripcion) {this.FormInscripcion = FormInscripcion;}
    public void setEvaluacionesEstudiante(List<Evaluacion> EvaluacionesEstudiante) {this.EvaluacionesEstudiante = EvaluacionesEstudiante;}
    public void setRespuestasEstudiante(List<Respuesta> RespuestasEstudiante) {this.RespuestasEstudiante = RespuestasEstudiante;}
    public void setEstudiosCursadosEstudiante(List<Estudio> EstudiosCursadosEstudiante) {this.EstudiosCursadosEstudiante = EstudiosCursadosEstudiante;}

    public String getFormInscripcion() {return FormInscripcion;}
    public List<Evaluacion> getEvaluacionesEstudiante() {return EvaluacionesEstudiante;}
    public List<Respuesta> getRespuestasEstudiante() {return RespuestasEstudiante;}
    public List<Estudio> getEstudiosCursadosEstudiante() {return EstudiosCursadosEstudiante;}    
    
    /*  Respuestas  */
    public void addRespuestaEvaluacion(Respuesta RespuestaEvaluacion){this.RespuestasEstudiante.add(RespuestaEvaluacion);}    
    public void removeRespuestaEvaluacion(Respuesta RespuestaEvaluacion){this.RespuestasEstudiante.remove(RespuestaEvaluacion);}
    /*  Estudios Cursados   */
    public void addEstudioCursado(Estudio EstudioCursado){this.EstudiosCursadosEstudiante.add(EstudioCursado);}    
    public void removeEstudioCursado(Estudio EstudioCursado){this.EstudiosCursadosEstudiante.remove(EstudioCursado);}
    /*  Evaluacion Estudiante   */
    public void addEvaluacionEstudiante(Evaluacion EvaluacionEstudiante){this.EvaluacionesEstudiante.add(EvaluacionEstudiante);}
    public void removeEvaluacionEstudiante(Evaluacion EvaluacionEstudiante){this.EvaluacionesEstudiante.remove(EvaluacionEstudiante);}
}
