package Enumerados.EstadoCivil;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class EstadoCivil implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEstadoCivil;
    @Column(unique=true)
    private String EstadoCivil;

    public EstadoCivil() {}

    public EstadoCivil(String TipoDePregunta) {this.EstadoCivil = TipoDePregunta;}

    /*  Setters */
    public void setIdEstadoCivil(int IdEstadoCivil) {this.IdEstadoCivil = IdEstadoCivil;}
    public void setEstadoCivil(String EstadoCivil) {this.EstadoCivil = EstadoCivil;}
    
    /*  Getters */

    public int getIdEstadoCivil() {return IdEstadoCivil;}
    public String getEstadoCivil() {return EstadoCivil;}    
    
}
