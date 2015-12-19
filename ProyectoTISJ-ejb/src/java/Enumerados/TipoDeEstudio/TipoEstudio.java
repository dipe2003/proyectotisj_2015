package Enumerados.TipoDeEstudio;

import Usuario.Estudiante.Estudios.Estudio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class TipoEstudio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdTipoEstudio;
    @Column(unique=true)
    private String TipoDeEstudio;
    @OneToMany(mappedBy = "TipoDeEstudio")
    private List<Estudio> EstudiosTipoEstudio;

    //  Constructores
    public TipoEstudio() {}
    public TipoEstudio(String TipoDePregunta) {
        this.TipoDeEstudio = TipoDePregunta;
        EstudiosTipoEstudio = new ArrayList<>();
    }

    //  Getters
    public int getIdTipoEstudio() {return IdTipoEstudio;}
    public String getTipoDeEstudio() {return TipoDeEstudio;}
    public List<Estudio> getEstudioTipoEstudio() {return EstudiosTipoEstudio;}    
    
    //  Setters
    public void setIdTipoEstudio(int IdTipoEstudio) {this.IdTipoEstudio = IdTipoEstudio;}
    public void setTipoDeEstudio(String TipoDeEstudio) {this.TipoDeEstudio = TipoDeEstudio;}        
    public void setEstudioTipoEstudio(List<Estudio> EstudiosTipoEstudio) {this.EstudiosTipoEstudio = EstudiosTipoEstudio;}
    
    public void addEstudioTipoEstudio(Estudio EstudioTipoEstudio){
        this.EstudiosTipoEstudio.add(EstudioTipoEstudio);
        if(EstudioTipoEstudio.getTipoDeEstudio() == null || !EstudioTipoEstudio.getTipoDeEstudio().equals(this)){
            EstudioTipoEstudio.setTipoDeEstudio(this);
        }
    }
    
}
