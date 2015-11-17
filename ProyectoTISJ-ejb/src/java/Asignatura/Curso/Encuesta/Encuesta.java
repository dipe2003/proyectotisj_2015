
package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Curso;
import Usuario.Estudiante.Estudiante;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Encuesta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEncuesta;
    @Temporal(TemporalType.DATE)
    private Date FechaEncuesta;
    
    @OneToMany(mappedBy = "EncuestaCurso")
    private List<Curso> CursosEncuesta;
    
    @ManyToMany
    private List<Pregunta> PreguntasEncuesta;
    
    @ManyToMany
    private List<Estudiante> EstudiantesEncuesta;
    
    //  Constructores
    public Encuesta() {}
    
    public Encuesta(Date FechaEncuesta) {
        this.FechaEncuesta = FechaEncuesta;
        this.CursosEncuesta = new ArrayList<>();
        this.PreguntasEncuesta = new ArrayList<>();
        this.EstudiantesEncuesta = new ArrayList<>();
    }
    
    //  Getters
    public int getIdEncuesta() {return IdEncuesta;}
    public Date getFechaEncuesta() {return FechaEncuesta;}
    public List<Curso> getCursosEncuesta() {return CursosEncuesta;}
    public List<Pregunta> getPreguntasEncuesta() {return PreguntasEncuesta;}
    public List<Estudiante> getEstudiantesEncuesta() {return EstudiantesEncuesta;}
    
    //  Setters
    public void setIdEncuesta(int IdEncuesta) {this.IdEncuesta = IdEncuesta;}
    public void setFechaEncuesta(Date FechaEncuesta) {this.FechaEncuesta = FechaEncuesta;}
    public void setCursosEncuesta(List<Curso> CursoEncuesta) {this.CursosEncuesta = CursoEncuesta;}
    public void setPreguntasEncuesta(List<Pregunta> PreguntasEncuesta) {this.PreguntasEncuesta = PreguntasEncuesta;}
    public void setEstudiantesEncuesta(List<Estudiante> EstudiantesEncuesta) {this.EstudiantesEncuesta = EstudiantesEncuesta;}
    
    // Cursos
    public void addCursoEncuesta(Curso CursoEncuesta){
        this.CursosEncuesta.add(CursoEncuesta);
        if (!CursoEncuesta.getEncuestaCurso().equals(this)) {
            CursoEncuesta.setEncuestaCurso(this);
        }
    }
    
    //  Preguntas
    public void addPreguntaEncuesta(Pregunta PreguntaEncuesta){
        this.PreguntasEncuesta.add(PreguntaEncuesta);
        if (!PreguntaEncuesta.getEncuestasPregunta().contains(this)) {
            PreguntaEncuesta.getEncuestasPregunta().add(this);
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
