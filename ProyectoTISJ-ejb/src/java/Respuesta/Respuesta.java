package Respuesta;

import Pregunta.Pregunta;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Respuesta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdRespuesta;
    private int RespultadoRespuesta;
    
    @OneToOne
    private Pregunta PreguntaRespuesta;

    //  Construtores
    public Respuesta() {}
    public Respuesta(int IdRespuesta, int RespultadoRespuesta, Pregunta PreguntaRespuesta) {
        this.IdRespuesta = IdRespuesta;
        this.RespultadoRespuesta = RespultadoRespuesta;
        this.PreguntaRespuesta = PreguntaRespuesta;
    }

    //  Getters
    public int getIdRespuesta() {return IdRespuesta;}
    public int getRespultadoRespuesta() {return RespultadoRespuesta;}
    public Pregunta getPreguntaRespuesta() {return PreguntaRespuesta;}
    
    //  Setters
    public void setIdRespuesta(int IdRespuesta) {this.IdRespuesta = IdRespuesta;}
    public void setRespultadoRespuesta(int RespultadoRespuesta) {this.RespultadoRespuesta = RespultadoRespuesta;}
    public void setPreguntaRespuesta(Pregunta PreguntaRespuesta) {this.PreguntaRespuesta = PreguntaRespuesta;}
}
