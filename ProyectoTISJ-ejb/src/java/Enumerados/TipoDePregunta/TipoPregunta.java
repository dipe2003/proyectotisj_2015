package Enumerados.TipoDePregunta;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoPregunta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdTipoPregunta;
    @Column(unique=true)
    private String TipoDePregunta;

    //  Constructores
    public TipoPregunta() {}
    public TipoPregunta(String TipoDePregunta) {this.TipoDePregunta = TipoDePregunta;}

    //  Getters
    public int getIdTipoPregunta() {return IdTipoPregunta;}
    public String getTipoDePregunta() {return TipoDePregunta;}    
    
    //  Setters
    public void setIdTipoPregunta(int IdTipoPregunta) {this.IdTipoPregunta = IdTipoPregunta;}
    public void setTipoDePregunta(String TipoDePregunta) {this.TipoDePregunta = TipoDePregunta;}
        
}
