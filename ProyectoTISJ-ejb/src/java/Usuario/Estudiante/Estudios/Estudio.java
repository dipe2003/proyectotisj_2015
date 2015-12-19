
package Usuario.Estudiante.Estudios;

import Enumerados.TipoDeEstudio.TipoEstudio;
import Usuario.Estudiante.Estudiante;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Estudio implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEstudio;
    @OneToOne
    @JoinColumn(name="TipoEstudio_Id", insertable=true, updatable=true)
    private TipoEstudio TipoDeEstudio;
    private String OrientacionEstudio;
    @ManyToOne
    private Estudiante EstudianteEstudioCursado;
    
    //  Constructores
    public Estudio() {}    
    public Estudio(TipoEstudio TipoEstudio, String OrientacionEstudio) {
        this.TipoDeEstudio = TipoEstudio;
        this.OrientacionEstudio = OrientacionEstudio;
    }

    //  Getters
    public int getIdEstudio() {return IdEstudio;}    
    public String getOrientacionEstudio() {return OrientacionEstudio;}  
    public TipoEstudio getTipoDeEstudio() {return TipoDeEstudio;}
    public Estudiante getEstudianteEstudioCursado() {return EstudianteEstudioCursado;}
    
    //  Setters
    public void setIdEstudio(int IdEstudio) {this.IdEstudio = IdEstudio;}
    public void setOrientacionEstudio(String OrientacionEstudio) {this.OrientacionEstudio = OrientacionEstudio;}
    public void setTipoDeEstudio(TipoEstudio TipoDeEstudio) {
        this.TipoDeEstudio = TipoDeEstudio;
        if(!TipoDeEstudio.getEstudioTipoEstudio().contains(this)){
            TipoDeEstudio.addEstudioTipoEstudio(this);
        }
    }
    public void setEstudianteEstudioCursado(Estudiante EstudianteEstudioCursado) {
        this.EstudianteEstudioCursado = EstudianteEstudioCursado;
        if(!EstudianteEstudioCursado.getEstudiosCursadosEstudiante().contains(this)){
            EstudianteEstudioCursado.addEstudioCursado(this);
        }
    }
    
}
