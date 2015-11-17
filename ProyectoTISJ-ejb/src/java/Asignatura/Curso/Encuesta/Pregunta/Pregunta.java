
package Asignatura.Curso.Encuesta.Pregunta;

import Asignatura.Curso.Encuesta.Pregunta.Respuesta.Respuesta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pregunta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdPregunta;
    private String TextoPregunta;
    private EnumTipoPregunta TipoPregunta;
    
    @OneToMany(mappedBy = "PreguntaRespuesta")
    private List<Respuesta> RespuestasPregunta;
    
    //  Constructores
    public Pregunta() {}
    public Pregunta(String TextoPregunta,  EnumTipoPregunta TipoPregunta) {
        this.TextoPregunta = TextoPregunta;
        this.TipoPregunta = TipoPregunta;
        this.RespuestasPregunta = new ArrayList<>();
    }
    
    //  Getters
    public int getIdPregunta() {return IdPregunta;}
    public String getTextoPregunta() {return TextoPregunta;}
    public EnumTipoPregunta getTipoPregunta() {return TipoPregunta;}
    public List<Respuesta> getRespuestasPregunta() {return RespuestasPregunta;}
    
    //  Setters
    public void setIdPregunta(int IdPregunta) {this.IdPregunta = IdPregunta;}
    public void setTextoPregunta(String TextoPregunta) {this.TextoPregunta = TextoPregunta;}
    public void setTipoPregunta(EnumTipoPregunta TipoPregunta) {this.TipoPregunta = TipoPregunta;}
    public void setRespuestasPregunta(List<Respuesta> RespuestasPregunta) {this.RespuestasPregunta = RespuestasPregunta;}
    
    //  Respuestas
    public void addRespuestaPregunta(Respuesta RespuestaPregunta){
        this.RespuestasPregunta.add(RespuestaPregunta);
        if (!RespuestaPregunta.getPreguntaRespuesta().equals(this)) {
            RespuestaPregunta.setPreguntaRespuesta(this);
        }
    }
}
