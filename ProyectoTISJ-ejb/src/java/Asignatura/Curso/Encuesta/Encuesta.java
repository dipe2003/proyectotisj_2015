
package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Curso;
import Asignatura.Curso.Encuesta.Pregunta.EnumTipoPregunta;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Usuario.Estudiante.Estudiante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    
    @OneToMany(mappedBy = "EncuestaRespuestas", fetch = FetchType.EAGER)
    private List<RespuestaEncuesta> RespuestasEncuesta;
    
    //  Constructores
     
    public Encuesta() {
        this.EstudiantesEncuesta = new ArrayList<>();
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
        this.CursoEncuesta = CursoEncuesta;
        if(CursoEncuesta.getEncuestaCurso()==null || !CursoEncuesta.getEncuestaCurso().equals(this)){
            CursoEncuesta.setEncuestaCurso(this);
        }
    }
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
    
    /**
     * Recorre la lista de respuestas-encuestas y devuelve cada pregunta.
     * @return 
     */
    public List<Pregunta> getPreguntasEncuesta(){
        List<Pregunta> preguntas = new ArrayList<>();
        for (RespuestaEncuesta item : RespuestasEncuesta){
            preguntas.add(item.getPreguntaRespuestasEncuesta());
        }
        return preguntas;
    }
    /**
     * Recorre la lista de respuestas-encuestas y devuelve cada pregunta sobre el docente.
     * @return 
     */
    public List<Pregunta> getPreguntasEncuestaDocente(){
        List<Pregunta> preguntas = new ArrayList<>();
        for (RespuestaEncuesta item : RespuestasEncuesta){
            if(item.getPreguntaRespuestasEncuesta().getTipoPregunta().equals(EnumTipoPregunta.Docente))
            preguntas.add(item.getPreguntaRespuestasEncuesta());
        }
        return preguntas;
    }
    /**
     * Recorre la lista de respuestas-encuestas y devuelve cada pregunta sobre el curso.
     * @return 
     */
    public List<Pregunta> getPreguntasEncuestaCurso(){
        List<Pregunta> preguntas = new ArrayList<>();
        for (RespuestaEncuesta item : RespuestasEncuesta){
            if(item.getPreguntaRespuestasEncuesta().getTipoPregunta().equals(EnumTipoPregunta.Curso))
            preguntas.add(item.getPreguntaRespuestasEncuesta());
        }
        return preguntas;
    }
}
