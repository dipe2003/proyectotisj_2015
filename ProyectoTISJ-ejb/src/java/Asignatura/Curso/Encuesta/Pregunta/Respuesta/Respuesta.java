package Asignatura.Curso.Encuesta.Pregunta.Respuesta;

import Asignatura.Curso.Encuesta.Encuesta;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Respuesta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdRespuesta;
    private int ResultadoRespuesta;
    
    @ManyToOne
    private Pregunta PreguntaRespuesta;
    
    @ManyToOne
    private Encuesta EncuestaRespuesta;

    //  Construtores
    public Respuesta() {}

    public Respuesta(int ResultadoRespuesta, Pregunta PreguntaRespuesta, Encuesta EncuestaRespuesta) {
        this.ResultadoRespuesta = ResultadoRespuesta;
        this.PreguntaRespuesta = PreguntaRespuesta;
        this.EncuestaRespuesta = EncuestaRespuesta;
    }    

    //  Getters
    public int getIdRespuesta() {return IdRespuesta;}
    public int getResultadoRespuesta() {return ResultadoRespuesta;}
    public Pregunta getPreguntaRespuesta() {return PreguntaRespuesta;}
    public Encuesta getEncuestaRespuesta() {return EncuestaRespuesta;}
    
    //  Setters
    public void setIdRespuesta(int IdRespuesta) {this.IdRespuesta = IdRespuesta;}
    public void setResultadoRespuesta(int ResultadoRespuesta) {this.ResultadoRespuesta = ResultadoRespuesta;}
    public void setPreguntaRespuesta(Pregunta PreguntaRespuesta) {
        this.PreguntaRespuesta = PreguntaRespuesta;
        if (!PreguntaRespuesta.getRespuestasPregunta().contains(this)) {
            PreguntaRespuesta.getRespuestasPregunta().add(this);
        }
    }
    public void setEncuestaRespuesta(Encuesta EncuestaRespuesta) {
        if(!EncuestaRespuesta.getRespuestasEncuesta().contains(this)){
            EncuestaRespuesta.getRespuestasEncuesta().add(this);
        }
        this.EncuestaRespuesta = EncuestaRespuesta;
    }
    
}
