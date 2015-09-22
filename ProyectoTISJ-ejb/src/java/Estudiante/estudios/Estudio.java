
package Estudiante.estudios;

import Enumerados.TipoDeEstudio.TipoEstudio;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Estudio implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEstudio;
    @ManyToOne
    private TipoEstudio TipoDeEstudio;
    private String OrientacionEstudio;
    
    public Estudio() {}
    
    public Estudio(TipoEstudio TipoEstudio, String OrientacionEstudio) {
        this.TipoDeEstudio = TipoEstudio;
        this.OrientacionEstudio = OrientacionEstudio;
    }

    /*  Setters */
    public void setIdEstudio(int IdEstudio) {this.IdEstudio = IdEstudio;}
    public void setTipoEstudio(TipoEstudio TipoEstudio) {this.TipoDeEstudio = TipoEstudio;}
    public void setOrientacionEstudio(String OrientacionEstudio) {this.OrientacionEstudio = OrientacionEstudio;}
    
    /*  Getters */
    public TipoEstudio getTipoEstudio() {return TipoDeEstudio;}
    public int getIdEstudio() {return IdEstudio;}    
    public String getOrientacionEstudio() {return OrientacionEstudio;}    
    
}
