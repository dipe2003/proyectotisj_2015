
package Evaluacion;

import Curso.Curso;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evaluacion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdEvaluacion;
    @Temporal(TemporalType.DATE)
    private Date FechaEvaluacion;
    
    @OneToOne
    private Curso CursoEvaluacion;

    public Evaluacion() {}

    public Evaluacion(Date FechaEvaluacion, Curso CursoEvaluacion) {
        this.FechaEvaluacion = FechaEvaluacion;
        this.CursoEvaluacion = CursoEvaluacion;
    }   

    public int getIdEvaluacion() {return IdEvaluacion;}

    public void setIdEvaluacion(int IdEvaluacion) {this.IdEvaluacion = IdEvaluacion;}

    public Date getFechaEvaluacion() {return FechaEvaluacion;}

    public void setFechaEvaluacion(Date FechaEvaluacion) {this.FechaEvaluacion = FechaEvaluacion;}

    public Curso getCursoEvaluacion() {return CursoEvaluacion;}

    public void setCursoEvaluacion(Curso CursoEvaluacion) {this.CursoEvaluacion = CursoEvaluacion;}
    
    
}
