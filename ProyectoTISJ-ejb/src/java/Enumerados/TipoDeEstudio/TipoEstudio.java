package Enumerados.TipoDeEstudio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class TipoEstudio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdTipoEstudio;
    @Column(unique=true)
    private String TipoDeEstudio;

    public TipoEstudio() {}

    public TipoEstudio(String TipoDePregunta) {this.TipoDeEstudio = TipoDePregunta;}

    /*  Setters */
    public void setIdTipoEstudio(int IdTipoEstudio) {this.IdTipoEstudio = IdTipoEstudio;}
    public void setTipoDeEstudio(String TipoDeEstudio) {this.TipoDeEstudio = TipoDeEstudio;}
    
    /*  Getters */

    public int getIdTipoEstudio() {return IdTipoEstudio;}
    public String getTipoDeEstudio() {return TipoDeEstudio;}    
    
}
