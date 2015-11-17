package Asignatura.Curso.Encuesta.Pregunta.Respuesta;

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

    //  Construtores
    public Respuesta() {}
    public Respuesta(int IdRespuesta, int RespultadoRespuesta, Pregunta PreguntaRespuesta) {
        this.IdRespuesta = IdRespuesta;
        this.ResultadoRespuesta = RespultadoRespuesta;
        this.PreguntaRespuesta = PreguntaRespuesta;
    }

    //  Getters
    public int getIdRespuesta() {return IdRespuesta;}
    public int getResultadoRespuesta() {return ResultadoRespuesta;}
    public Pregunta getPreguntaRespuesta() {return PreguntaRespuesta;}
    
    //  Setters
    public void setIdRespuesta(int IdRespuesta) {this.IdRespuesta = IdRespuesta;}
    public void setResultadoRespuesta(int ResultadoRespuesta) {this.ResultadoRespuesta = ResultadoRespuesta;}
    public void setPreguntaRespuesta(Pregunta PreguntaRespuesta) {
        this.PreguntaRespuesta = PreguntaRespuesta;
        if (!PreguntaRespuesta.getRespuestasPregunta().contains(this)) {
            PreguntaRespuesta.getRespuestasPregunta().add(this);
        }
    }
}
