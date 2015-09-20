
package Estudiante.estudios;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estudio implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEstudio;
    private EnumTipoEstudio TipoEstudio;
    private String OrientacionEstudio;

    public Estudio() {}

    public Estudio(EnumTipoEstudio TipoEstudio, String OrientacionEstudio) {
        this.TipoEstudio = TipoEstudio;
        this.OrientacionEstudio = OrientacionEstudio;
    }

    public int getIdEstudio() {return IdEstudio;}

    public void setIdEstudio(int IdEstudio) {this.IdEstudio = IdEstudio;}

    public EnumTipoEstudio getTipoEstudio() {return TipoEstudio;}

    public void setTipoEstudio(EnumTipoEstudio TipoEstudio) {this.TipoEstudio = TipoEstudio;}

    public String getOrientacionEstudio() {return OrientacionEstudio;}

    public void setOrientacionEstudio(String OrientacionEstudio) {this.OrientacionEstudio = OrientacionEstudio;}    
    
}
