package Asignatura.Curso.Encuesta.Pregunta.Respuesta;

import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Asignatura.Curso.Encuesta.RespuestaEncuesta;
import java.io.Serializable;
import javax.persistence.CascadeType;
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
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    private RespuestaEncuesta respuestaEncuesta;

    //  Constructores
    public Respuesta() {}

    public Respuesta(int ResultadoRespuesta, Pregunta PreguntaRespuesta) {
        this.ResultadoRespuesta = ResultadoRespuesta;
        this.PreguntaRespuesta = PreguntaRespuesta;
    }    

    //  Getters
    public int getIdRespuesta() {return IdRespuesta;}
    public int getResultadoRespuesta() {return ResultadoRespuesta;}
    public Pregunta getPreguntaRespuesta() {return PreguntaRespuesta;}
    public RespuestaEncuesta getRespuestaEncuesta() {return respuestaEncuesta;}
    
    //  Setters
    public void setIdRespuesta(int IdRespuesta) {this.IdRespuesta = IdRespuesta;}
    public void setResultadoRespuesta(int ResultadoRespuesta) {this.ResultadoRespuesta = ResultadoRespuesta;}
    public void setPreguntaRespuesta(Pregunta PreguntaRespuesta) {
        this.PreguntaRespuesta = PreguntaRespuesta;
        if (!PreguntaRespuesta.getRespuestasPregunta().contains(this)) {
            PreguntaRespuesta.getRespuestasPregunta().add(this);
        }
    }
    public void setRespuestaEncuesta(RespuestaEncuesta respuestaEncuesta) {
        if (respuestaEncuesta != null && !respuestaEncuesta.getRespuestasEncuesta().contains(this)) {
            respuestaEncuesta.getRespuestasEncuesta().add(this);
        }
        this.respuestaEncuesta = respuestaEncuesta;}
    
    
}
