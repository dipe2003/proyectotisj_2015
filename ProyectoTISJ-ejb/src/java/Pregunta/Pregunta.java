
package Pregunta;

import Encuesta.Encuesta;
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
    
    @OneToMany
    private List<Encuesta> EncuestasPregunta;
    
    //  Constructores
    public Pregunta() {}
    public Pregunta(String TextoPregunta,  EnumTipoPregunta TipoPregunta) {
        this.TextoPregunta = TextoPregunta;
        this.TipoPregunta = TipoPregunta;
        this.EncuestasPregunta = new ArrayList<>();
    }
    
    //  Getters
    public int getIdPregunta() {return IdPregunta;}
    public String getTextoPregunta() {return TextoPregunta;}
    public EnumTipoPregunta getTipoPregunta() {return TipoPregunta;}
    public List<Encuesta> getEncuestasPregunta() {return EncuestasPregunta;}
    
    //  Setters
    public void setIdPregunta(int IdPregunta) {this.IdPregunta = IdPregunta;}
    public void setTextoPregunta(String TextoPregunta) {this.TextoPregunta = TextoPregunta;}
    public void setTipoPregunta(EnumTipoPregunta TipoPregunta) {this.TipoPregunta = TipoPregunta;}
    public void setEncuestasPregunta(List<Encuesta> EncuestasPregunta) {this.EncuestasPregunta = EncuestasPregunta;}
    
    //   Encuestas
    public void addEncuestaPregunta(Encuesta EncuestaPregunta){this.EncuestasPregunta.add(EncuestaPregunta);}
    public void removeEncuestaPregunta(Encuesta EncuestaPregunta){this.EncuestasPregunta.remove(EncuestaPregunta);}
    
}
