
package Estudiante;

import Encuesta.Encuesta;
import Evaluacion.Evaluacion;
import Usuario.Usuario;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Estudiante extends Usuario {
    private String FormInscripcion;
    
    @OneToMany
    private List<Evaluacion> EvaluacionesEstudiante;
    
    @OneToMany
    private List<Encuesta> EncuestasEstudiante;

    public Estudiante(String FormInscripcion, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario) {
        super(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario);
        this.FormInscripcion = FormInscripcion;
    }

    public Estudiante() {}

    public String getFormInscripcion() {return FormInscripcion;}

    public void setFormInscripcion(String FormInscripcion) {this.FormInscripcion = FormInscripcion;}

    public List<Evaluacion> getEvaluacionesEstudiante() {return EvaluacionesEstudiante;}

    public void setEvaluacionesEstudiante(List<Evaluacion> EvaluacionesEstudiante) {this.EvaluacionesEstudiante = EvaluacionesEstudiante;}

    public List<Encuesta> getEncuestasEstudiante() {return EncuestasEstudiante;}

    public void setEncuestasEstudiante(List<Encuesta> EncuestasEstudiante) {this.EncuestasEstudiante = EncuestasEstudiante;}    
    
    
}
