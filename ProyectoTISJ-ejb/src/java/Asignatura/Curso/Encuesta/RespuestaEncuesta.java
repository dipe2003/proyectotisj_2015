
package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Asignatura.Curso.Encuesta.Pregunta.Respuesta.Respuesta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RespuestaEncuesta implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdRespuestaEncuesta;
    @OneToMany(mappedBy = "respuestaEncuesta")
    private List<Respuesta> RespuestasEncuesta;
    @ManyToOne
    private Pregunta PreguntaRespuestasEncuesta;
    
    @ManyToOne
    private Encuesta EncuestaRespuestas;
    
    //  Constructores

    public RespuestaEncuesta() {}
    
    public RespuestaEncuesta(Pregunta PreguntaEncuesta, Encuesta EncuestaRespuestas){
        this.RespuestasEncuesta = new ArrayList<>();
        this.PreguntaRespuestasEncuesta = PreguntaEncuesta;
        this.EncuestaRespuestas = EncuestaRespuestas;
    }
    
    //  Getters
    public int getIdRespuestaEncuesta() {return IdRespuestaEncuesta;}
    public List<Respuesta> getRespuestasEncuesta() {return RespuestasEncuesta;}
    public Pregunta getPreguntaRespuestasEncuesta() {return PreguntaRespuestasEncuesta;}
    public Encuesta getEncuestaRespuestas() {return EncuestaRespuestas;}
    
    //  Setters
    public void setIdRespuestaEncuesta(int IdRespuestaEncuesta) {this.IdRespuestaEncuesta = IdRespuestaEncuesta;}
    public void setRespuestasEncuesta(List<Respuesta> RespuestasEncuesta) {this.RespuestasEncuesta = RespuestasEncuesta;}
    public void setPreguntaRespuestasEncuesta(Pregunta PreguntaRespuestasEncuesta) {this.PreguntaRespuestasEncuesta = PreguntaRespuestasEncuesta;} 
    public void setEncuestaRespuestas(Encuesta EncuestaRespuestas) {
        if (!EncuestaRespuestas.getRespuestasEncuesta().contains(this)) {
            EncuestaRespuestas.getRespuestasEncuesta().add(this);
        }
        this.EncuestaRespuestas = EncuestaRespuestas;}
    
    // Respuestas
    public void addRespuestaEncuesta(Respuesta RespuestaEncuesta){
        this.RespuestasEncuesta.add(RespuestaEncuesta);
        if (!RespuestaEncuesta.getRespuestaEncuesta().equals(this)) {
            RespuestaEncuesta.setRespuestaEncuesta(this);
        }
    }
    
}
