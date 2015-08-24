
package Pregunta;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pregunta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdPregunta;
    private String TextoPregunta;
    private int ResultadoPregunta;
    private EnumTipoPregunta TipoPregunta;
    
    public Pregunta() {
    }

    public Pregunta(String TextoPregunta, int ResultadoPregunta, EnumTipoPregunta TipoPregunta) {
        this.TextoPregunta = TextoPregunta;
        this.ResultadoPregunta = ResultadoPregunta;
        this.TipoPregunta = TipoPregunta;
    }

    public int getIdPregunta() {
        return IdPregunta;
    }

    public void setIdPregunta(int IdPregunta) {
        this.IdPregunta = IdPregunta;
    }

    public String getTextoPregunta() {
        return TextoPregunta;
    }

    public void setTextoPregunta(String TextoPregunta) {
        this.TextoPregunta = TextoPregunta;
    }

    public int getResultadoPregunta() {
        return ResultadoPregunta;
    }

    public void setResultadoPregunta(int ResultadoPregunta) {
        this.ResultadoPregunta = ResultadoPregunta;
    }

    public EnumTipoPregunta getTipoPregunta() {
        return TipoPregunta;
    }

    public void setTipoPregunta(EnumTipoPregunta TipoPregunta) {
        this.TipoPregunta = TipoPregunta;
    }

   
    
    
}
