
package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Asignatura.Curso.Encuesta.Pregunta.Respuesta.Respuesta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RespuestaEncuesta implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdRespuestaEncuesta;
    @OneToMany(mappedBy = "respuestaEncuesta", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Respuesta> RespuestasEncuesta;
    @ManyToOne
    private Pregunta PreguntaRespuestasEncuesta;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
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
        if(EncuestaRespuestas == null && this.EncuestaRespuestas != null){
            this.EncuestaRespuestas.getRespuestasEncuesta().remove(this);
        }
        if (EncuestaRespuestas != null && !EncuestaRespuestas.getRespuestasEncuesta().contains(this)) {
            EncuestaRespuestas.getRespuestasEncuesta().add(this);
        }
        this.EncuestaRespuestas = EncuestaRespuestas;}
    
    // Respuestas
    public void addRespuestaEncuesta(Respuesta RespuestaEncuesta){
        this.RespuestasEncuesta.add(RespuestaEncuesta);
        if (RespuestaEncuesta.getRespuestaEncuesta()==null || !RespuestaEncuesta.getRespuestaEncuesta().equals(this)) {
            RespuestaEncuesta.setRespuestaEncuesta(this);
        }
    }
    
    public void removeRespuestaEncuesta(Respuesta RespuestaEncuesta){
        this.RespuestasEncuesta.remove(RespuestaEncuesta);
        if(RespuestaEncuesta.getRespuestaEncuesta() != null && RespuestaEncuesta.getRespuestaEncuesta().equals(this)){
            RespuestaEncuesta.setRespuestaEncuesta(null);
        }
    } 
    
    //  Resultados
    public float getPromedioResultado(){
        float promedio = 0f;
        for(Respuesta res: this.RespuestasEncuesta){
            promedio += res.getResultadoRespuesta();
        }
        promedio = promedio/(this.RespuestasEncuesta.size());
        return promedio;
    }
    
    public String getTipoPregunta(){
        return this.PreguntaRespuestasEncuesta.getTipoPregunta().toString();
    }
    
}
