
package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Curso;
import Usuario.Estudiante.Estudiante;
import Asignatura.Curso.Encuesta.Pregunta.Respuesta.Respuesta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Encuesta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEncuesta;
    @Temporal(TemporalType.DATE)
    private Date FechaEncuesta;
    
    @OneToOne(mappedBy = "EncuestaCurso")
    private Curso CursoEncuesta;
    
    @OneToMany
    private List<Respuesta> RespuestasEncuesta;
    
    @ManyToMany
    private List<Estudiante> EstudiantesEncuesta;
    
    //  Constructores
    public Encuesta() {}
    
    public Encuesta(Date FechaEncuesta) {
        this.FechaEncuesta = FechaEncuesta;
        this.RespuestasEncuesta = new ArrayList<>();
        this.EstudiantesEncuesta = new ArrayList<>();
    }
    
    //  Getters
    public int getIdEncuesta() {return IdEncuesta;}
    public Date getFechaEncuesta() {return FechaEncuesta;}
    public Curso getCursosEncuesta() {return CursoEncuesta;}
    public List<Respuesta> getRespuestasEncuesta() {return RespuestasEncuesta;}
    public List<Estudiante> getEstudiantesEncuesta() {return EstudiantesEncuesta;}
    
    //  Setters
    public void setIdEncuesta(int IdEncuesta) {this.IdEncuesta = IdEncuesta;}
    public void setFechaEncuesta(Date FechaEncuesta) {this.FechaEncuesta = FechaEncuesta;}
    public void setCursosEncuesta(Curso CursoEncuesta) {
        this.CursoEncuesta = CursoEncuesta;
        if (!CursoEncuesta.getEncuestaCurso().equals(this)) {
            CursoEncuesta.setEncuestaCurso(this);
        }
    }
    public void setRespuestasEncuesta(List<Respuesta> RespuestasEncuesta) {this.RespuestasEncuesta = RespuestasEncuesta;}
    public void setEstudiantesEncuesta(List<Estudiante> EstudiantesEncuesta) {this.EstudiantesEncuesta = EstudiantesEncuesta;}
    
    // Cursos

    //  Respuesta
    public void addRespuestaEncuesta(Respuesta RespuestaEncuesta){
        this.RespuestasEncuesta.add(RespuestaEncuesta);
        if (!RespuestaEncuesta.getEncuestaRespuesta().equals(this)) {
            RespuestaEncuesta.setEncuestaRespuesta(this);
        }
    }
    
    //  Estudiante
    public void addEstudianteEncuesta(Estudiante EstudianteEncuesta){
        this.EstudiantesEncuesta.add(EstudianteEncuesta);
        if (!EstudianteEncuesta.getEncuestasEstudiante().contains(this)) {
            EstudianteEncuesta.getEncuestasEstudiante().add(this);
        }
    }
}
