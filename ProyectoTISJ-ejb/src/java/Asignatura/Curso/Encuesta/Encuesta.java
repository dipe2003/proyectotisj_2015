
package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Curso;
import Usuario.Estudiante.Estudiante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Encuesta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEncuesta;
    
    @OneToOne(mappedBy = "EncuestaCurso")
    private Curso CursoEncuesta;
    
    @ManyToMany
    private List<Estudiante> EstudiantesEncuesta;
    
    @OneToMany(mappedBy = "EncuestaRespuestas")
    private List<RespuestaEncuesta> RespuestasEncuesta;
    
    //  Constructores
    public Encuesta() {}
    
    public Encuesta(Curso curso) {
        this.EstudiantesEncuesta = new ArrayList<>();
        this.CursoEncuesta = curso;
        this.RespuestasEncuesta = new ArrayList<>();
    }
    
    
    public Encuesta(Curso curso, List<RespuestaEncuesta> RespuestasEncuestas) {
        this.EstudiantesEncuesta = new ArrayList<>();
        this.CursoEncuesta = curso;
        this.RespuestasEncuesta = RespuestasEncuestas;
    }
    
    //  Getters
    public int getIdEncuesta() {return IdEncuesta;}
    public List<Estudiante> getEstudiantesEncuesta() {return EstudiantesEncuesta;}
    public Curso getCursoEncuesta() {return CursoEncuesta;}
    public List<RespuestaEncuesta> getRespuestasEncuesta() {return RespuestasEncuesta;}
    
    //  Setters
    public void setIdEncuesta(int IdEncuesta) {this.IdEncuesta = IdEncuesta;}
    public void setEstudiantesEncuesta(List<Estudiante> EstudiantesEncuesta) {this.EstudiantesEncuesta = EstudiantesEncuesta;}
    public void setCursoEncuesta(Curso CursoEncuesta) {
        if (!CursoEncuesta.getEncuestaCurso().equals(this)) {
            CursoEncuesta.setEncuestaCurso(this);
        }
        this.CursoEncuesta = CursoEncuesta;}
    public void setRespuestasEncuesta(List<RespuestaEncuesta> RespuestasEncuesta) {this.RespuestasEncuesta = RespuestasEncuesta;}
    
    //  Estudiante
    public void addEstudianteEncuesta(Estudiante EstudianteEncuesta){
        this.EstudiantesEncuesta.add(EstudianteEncuesta);
        if (!EstudianteEncuesta.getEncuestasEstudiante().contains(this)) {
            EstudianteEncuesta.getEncuestasEstudiante().add(this);
        }
    }
    
    //  Preguntas / Respuestas
    public void addRespuestaEncuesta(RespuestaEncuesta respuestaEncuesta){
        this.RespuestasEncuesta.add(respuestaEncuesta);
        if (!respuestaEncuesta.getEncuestaRespuestas().equals(this)) {
            respuestaEncuesta.setEncuestaRespuestas(this);
        }
    }
}
