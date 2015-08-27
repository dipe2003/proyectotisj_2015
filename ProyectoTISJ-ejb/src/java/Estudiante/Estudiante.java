
package Estudiante;

import Encuesta.Encuesta;
import Evaluacion.Evaluacion;
import Respuesta.Respuesta;
import Usuario.Usuario;
import java.util.ArrayList;
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

    public Estudiante(String FormInscripcion, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario) {
        super(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario);
        this.FormInscripcion = FormInscripcion;
        this.EvaluacionesEstudiante = new ArrayList<>();
        this.RespuestasEstudiante = new ArrayList<>();
    }

    public Estudiante() {}

    public String getFormInscripcion() {return FormInscripcion;}

    public void setFormInscripcion(String FormInscripcion) {this.FormInscripcion = FormInscripcion;}

    public List<Evaluacion> getEvaluacionesEstudiante() {return EvaluacionesEstudiante;}

    public void setEvaluacionesEstudiante(List<Evaluacion> EvaluacionesEstudiante) {this.EvaluacionesEstudiante = EvaluacionesEstudiante;}

    public List<Respuesta> getRespuestasEstudiante() {
        return RespuestasEstudiante;
    }

    public void setRespuestasEstudiante(List<Respuesta> RespuestasEstudiante) {
        this.RespuestasEstudiante = RespuestasEstudiante;
    }

    public void addRespuestaEstudiante(Respuesta RespuestaEstudiante){
        this.RespuestasEstudiante.add(RespuestaEstudiante);
    }
    
    public void removeRespuestaEstudiante(Respuesta RespuestaEstudiante){
        this.RespuestasEstudiante.remove(RespuestaEstudiante);
    }
    
    public void addEvalucionEstudiante(Evaluacion EvaluacionEstudiante){
        this.EvaluacionesEstudiante.add(EvaluacionEstudiante);
    }
    
    public void removeEvaluacionEstudiante(Evaluacion EvaluacionEstudiante){
        this.EvaluacionesEstudiante.remove(EvaluacionEstudiante);
    }
    
}
