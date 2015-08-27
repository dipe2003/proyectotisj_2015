
package Pregunta;

import Encuesta.Encuesta;
import java.io.Serializable;
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
    
    public Pregunta() {}

    public Pregunta(String TextoPregunta,  EnumTipoPregunta TipoPregunta) {
        this.TextoPregunta = TextoPregunta;
        this.TipoPregunta = TipoPregunta;
    }

    public int getIdPregunta() {return IdPregunta;}

    public void setIdPregunta(int IdPregunta) {this.IdPregunta = IdPregunta;}

    public String getTextoPregunta() {return TextoPregunta;}

    public void setTextoPregunta(String TextoPregunta) {this.TextoPregunta = TextoPregunta;}
   
    public EnumTipoPregunta getTipoPregunta() {return TipoPregunta;}

    public void setTipoPregunta(EnumTipoPregunta TipoPregunta) {this.TipoPregunta = TipoPregunta;}

    public List<Encuesta> getEncuestasPregunta() {return EncuestasPregunta;}

    public void setEncuestasPregunta(List<Encuesta> EncuestasPregunta) {this.EncuestasPregunta = EncuestasPregunta;}

   public void addEncuestaPregunta(Encuesta EncuestaPregunta){this.EncuestasPregunta.add(EncuestaPregunta);}
    
   public void removeEncuestaPregunta(Encuesta EncuestaPregunta){this.EncuestasPregunta.remove(EncuestaPregunta);}
    
}
